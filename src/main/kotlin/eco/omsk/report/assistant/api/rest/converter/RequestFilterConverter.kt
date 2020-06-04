package eco.omsk.report.assistant.api.rest.converter

import eco.omsk.report.assistant.service.search.converter.FilterConverter
import eco.omsk.report.assistant.service.search.form.RequestFilterForm
import org.springframework.stereotype.Service

@Service
class RequestFilterConverter(filterConverter: FilterConverter) : RestRequestConverter<RequestFilterForm>(filterConverter) {
    override fun findPrefix(): String = "filters"
}