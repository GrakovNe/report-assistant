package eco.omsk.report.assistant.api.rest.converter

import eco.omsk.report.assistant.api.rest.response.ReportResponse
import eco.omsk.report.assistant.converter.BaseConverter
import eco.omsk.report.assistant.domain.entity.air.Report
import org.springframework.stereotype.Service

@Service
class ReportConverter : BaseConverter<Report, ReportResponse>({ ReportResponse() }) {

    override fun inflateResponse(incoming: Report, result: ReportResponse): ReportResponse {
        result.comment = incoming.comment
        result.intensity = incoming.intensity
        result.latitude = incoming.position?.latitude
        result.longitude = incoming.position?.longitude
        result.type = incoming.type?.name
        result.reporterId = incoming.reporter?.id

        return result
    }
}