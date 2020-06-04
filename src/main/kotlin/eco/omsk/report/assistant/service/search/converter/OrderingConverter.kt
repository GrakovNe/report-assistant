package eco.omsk.report.assistant.service.search.converter

import com.fasterxml.jackson.databind.ObjectMapper
import eco.omsk.report.assistant.service.search.form.RequestOrderingForm
import org.springframework.stereotype.Service

@Service
class OrderingConverter(objectMapper: ObjectMapper) : RequestConverter<RequestOrderingForm>(objectMapper) {

    override fun getRequestClass(): Class<RequestOrderingForm> = RequestOrderingForm::class.java
}