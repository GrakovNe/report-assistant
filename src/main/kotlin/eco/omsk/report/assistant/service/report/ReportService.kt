package eco.omsk.report.assistant.service.report

import eco.omsk.report.assistant.domain.entity.air.Report
import eco.omsk.report.assistant.domain.repository.air.ReportRepository
import eco.omsk.report.assistant.service.BaseService
import org.springframework.stereotype.Service

@Service
class ReportService(repository: ReportRepository, searchService: ReportSearchService) : BaseService<Report>(repository, searchService)