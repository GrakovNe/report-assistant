package eco.omsk.report.assistant.api.rest.response

import java.math.BigDecimal

class ReportResponse {
    var reporterId: Long? = null
    var latitude: BigDecimal? = null
    var longitude: BigDecimal? = null
    var intensity: Int? = null
    var type: String? = null
    var comment: String? = null
}