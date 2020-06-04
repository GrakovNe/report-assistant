package eco.omsk.report.assistant.service.search.form

class RequestFilterForm : RequestForm() {
    var exactlyValue: String? = null
    var startingWithValue: String? = null
    var endingWithValue: String? = null
    var likeValue: String? = null
    var maximalValue: String? = null
    var minimalValue: String? = null
}