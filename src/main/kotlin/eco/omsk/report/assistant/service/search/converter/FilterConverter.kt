package eco.omsk.report.assistant.service.search.converter

import com.fasterxml.jackson.databind.ObjectMapper
import eco.omsk.report.assistant.service.search.form.RequestFilterForm
import org.springframework.stereotype.Service

@Service
class FilterConverter(objectMapper: ObjectMapper) : RequestConverter<RequestFilterForm>(objectMapper) {

    override fun getRequestClass(): Class<RequestFilterForm> = RequestFilterForm::class.java
}