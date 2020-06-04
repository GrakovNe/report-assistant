package eco.omsk.report.assistant.api.rest.converter

import eco.omsk.report.assistant.service.search.converter.OrderingConverter
import eco.omsk.report.assistant.service.search.form.RequestOrderingForm
import org.springframework.stereotype.Service

@Service
class RequestOrderingConverter (filterConverter: OrderingConverter) : RestRequestConverter<RequestOrderingForm>(filterConverter) {
    override fun findPrefix(): String = "sorting"
}