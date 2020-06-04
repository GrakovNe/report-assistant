package eco.omsk.report.assistant.domain.search.report

import eco.omsk.report.assistant.domain.entity.air.Report
import eco.omsk.report.assistant.service.search.order.EntitySimpleOrdering
import org.springframework.stereotype.Service

@Service
class ReportOrdering : EntitySimpleOrdering<Report>() {
    override fun getOrderingFields(): List<String> = listOf("type", "intensity", "comment")
}