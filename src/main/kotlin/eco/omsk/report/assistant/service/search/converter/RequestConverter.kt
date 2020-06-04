package eco.omsk.report.assistant.service.search.converter

import com.fasterxml.jackson.databind.ObjectMapper
import eco.omsk.report.assistant.converter.BaseConverter
import eco.omsk.report.assistant.service.search.form.RequestForm
import java.util.*

/**
 * Knows how to represent a raw filter's map to well known form. Should be different that API converter due Single Responsibility Principe.
 *
 * THIS converter makes filters forms from ANY filter map
 * THAT converted makes filters form (with delegation to THIS) from HTTP Request params
 *
 * In case or another network interface (SOAP, MQ, etc) the another one decorator should be implemented above this converter.
 */
abstract class RequestConverter<T : RequestForm>(private val objectMapper: ObjectMapper) : BaseConverter<Map<String, String>, List<T>>({ listOf() }) {

    abstract fun getRequestClass(): Class<T>

    private val indexedPath = "[0-9]+".toRegex()

    override fun inflateResponse(incoming: Map<String, String>, result: List<T>): List<T> {
        val map: MutableMap<String, Any?> = TreeMap()

        for (key in incoming.keys) {
            val keyList = toFiltersList(key)
            val valueMap = createTree(keyList, map)
            val value = incoming[key]
            valueMap[keyList[keyList.size - 1]] = value
        }

        return map.entries.map { formsForField(it) }.flatten()
    }

    private fun formsForField(entry: MutableMap.MutableEntry<String, Any?>): List<T> {
        val forms = mutableListOf<T>()

        val filters = entry.value

        if (filters !is Map<*, *>) {
            throw IllegalArgumentException("Filters list is incorrect")
        }

        val simpleFilters = filters.entries.filter { !isIndexedFilter(it.key as String) }
        val indexedFilters = filters.entries.filter { isIndexedFilter(it.key as String) }

        if (indexedFilters.isNotEmpty()) {
            forms.addAll(indexedFilters.map { objectMapper.convertValue(it.value, getRequestClass()) })
        }

        if (simpleFilters.isNotEmpty()) {
            forms.add(objectMapper.convertValue(simpleFilters.map { Pair(it.key, it.value) }.toMap(), getRequestClass()))
        }

        return forms.map { it.apply { field = entry.key } }
    }

    private fun isIndexedFilter(it: String) = it.matches(indexedPath)

    /**
     * Wee need to turn flat mapped structure like [type][maximalValue] -> 7
     * to hierarchy oriented map.
     *
     * So, just a recursion to support any child levels
     *
     * UNCHECKED_CAST is acceptable due this is private method with the only one call above.
     */
    @Suppress("UNCHECKED_CAST")
    private fun createTree(keys: List<String>, map: MutableMap<String, Any?>): MutableMap<String, Any?> {
        var valueMap = map[keys.first()]

        if (valueMap == null) {
            valueMap = mutableMapOf<String, Any?>()
        }

        map[keys.first()] = valueMap

        var out: MutableMap<String, Any?> = valueMap as MutableMap<String, Any?>
        if (keys.size > 2) {
            out = createTree(keys.subList(1, keys.size), valueMap)
        }

        return out
    }

    private fun toFiltersList(key: String) = key.split("]\\[".toRegex()).map { it.replace("[", "]").replace("]", "") }.toList()
}