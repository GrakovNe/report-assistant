package eco.omsk.report.assistant.converter

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

abstract class BaseConverter<T, V>(protected val blankResponse: () -> V) {

    open fun convert(incoming: T?): V {
        val result = blankResponse.invoke()

        if (null == incoming) {
            return result
        }

        return inflateResponse(incoming, result)
    }

    protected abstract fun inflateResponse(incoming: T, result: V): V

    protected fun toDateTime(dateTime: LocalDateTime?): String? {
        if (null == dateTime) {
            return null
        }

        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime)
    }

    protected fun <T> toRawString(value: T?, vararg replacements: Pair<String, String> = emptyArray()): String? {
        if (null == value) {
            return null
        }

        var result = value.toString().trim()
        replacements.forEach { result = result.replace(it.first, it.second) }

        return result
    }
}