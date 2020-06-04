package eco.omsk.report.assistant.service.report

import eco.omsk.report.assistant.domain.entity.air.Report
import eco.omsk.report.assistant.service.search.BaseSearchService
import eco.omsk.report.assistant.service.search.EntityFilter
import eco.omsk.report.assistant.service.search.order.EntityOrdering
import org.springframework.stereotype.Service

@Service
class ReportSearchService(filters: List<EntityFilter<Report>>, ordering: List<EntityOrdering<Report>>) : BaseSearchService<Report>(filters, ordering)