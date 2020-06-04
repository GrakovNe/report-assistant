package eco.omsk.report.assistant.api.rest.converter

import eco.omsk.report.assistant.converter.BaseConverter
import eco.omsk.report.assistant.service.search.converter.RequestConverter
import eco.omsk.report.assistant.service.search.form.RequestForm


/**
 * Once we received a raw list of filters from API client, we need convert in to well-known forms for processing
 *
 * Filter example: filters[intensity][exactlyValue]=HIGHEST
 * Where:
 *  intensity - is key which filtration or search is need. It may be different from database / entity field
 *  exactlyValue - exact value which should be a condition. maximalValue and minimalValue are supported too for now
 *  HIGHEST = condition for filter
 *
 *  In this case the only LORO typed entities will be returned.
 *
 *  Filters could be chained in list via default HTTP Get parameters with "&" delimiter
 */
abstract class RestRequestConverter<T : RequestForm>(private val converter: RequestConverter<T>) : BaseConverter<Map<String, String>, List<T>>({ listOf() }) {

    abstract fun findPrefix(): String

    override fun inflateResponse(incoming: Map<String, String>, result: List<T>): List<T> {
        val filters = incoming
                .entries
                .filter { it.key.contains(findPrefix()) }
                .map { Pair(it.key.replace(findPrefix(), ""), it.value) }
                .toMap()

        return converter.convert(filters)
    }
}