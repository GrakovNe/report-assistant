package eco.omsk.report.assistant.service.search.form

import kotlin.properties.Delegates

open class RequestForm {
    var field: String by Delegates.notNull()
}