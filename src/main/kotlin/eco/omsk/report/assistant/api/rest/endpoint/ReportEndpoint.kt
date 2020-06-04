package eco.omsk.report.assistant.api.rest.endpoint

import eco.omsk.report.assistant.api.rest.converter.ReportConverter
import eco.omsk.report.assistant.api.rest.converter.RequestFilterConverter
import eco.omsk.report.assistant.api.rest.converter.RequestOrderingConverter
import eco.omsk.report.assistant.api.rest.response.ReportResponse
import eco.omsk.report.assistant.service.report.ReportService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/report")
class ReportEndpoint(private val filterConverter: RequestFilterConverter,
                     private val orderingConverter: RequestOrderingConverter,
                     private val service: ReportService,
                     private val reportConverter: ReportConverter) {

    @GetMapping
    fun findReports(@RequestParam params: Map<String, String>,
                    @RequestParam(name = "page", defaultValue = "0", required = false) page: Int,
                    @RequestParam(name = "size", defaultValue = "10", required = false) size: Int): Page<ReportResponse>? {

        return service
                .search(filterConverter.convert(params), orderingConverter.convert(params), page, size)
                .map { reportConverter.convert(it) }
    }


    @GetMapping("{id}")
    fun findReport(@PathVariable id: Long) = reportConverter.convert(service.requireEntity(id))

}