package eco.omsk.report.assistant.domain.search.report

import eco.omsk.report.assistant.domain.entity.air.Report
import eco.omsk.report.assistant.service.search.EntityFilter
import eco.omsk.report.assistant.service.search.FilterType
import eco.omsk.report.assistant.service.search.form.RequestFilterForm
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class ReportIntensityFilter : EntityFilter<Report>() {
    override fun getType(): FilterType = FilterType.VALUE_RANGE

    override fun getName(): String = "intensity"

    override fun buildSpecification(form: RequestFilterForm): Specification<Report> {
        return listOf(greaterThat(form), lessThat(form)).reduce(Specification<Report>::and)
    }

    fun greaterThat(form: RequestFilterForm): Specification<Report> {
        if (form.minimalValue != null) {
            return Specification { root, _, builder ->
                builder.greaterThanOrEqualTo(root.get<String>("intensity"), "${form.minimalValue}%")
            }
        }

        return Specification { _, _, builder -> builder.and() }
    }

    fun lessThat(form: RequestFilterForm): Specification<Report> {
        if (form.maximalValue != null) {
            return Specification { root, _, builder ->
                builder.greaterThanOrEqualTo(root.get<String>("intensity"), "${form.maximalValue}%")
            }
        }

        return Specification { _, _, builder -> builder.and() }
    }
}