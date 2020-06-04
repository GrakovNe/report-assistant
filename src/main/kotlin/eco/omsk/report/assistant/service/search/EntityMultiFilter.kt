package eco.omsk.report.assistant.service.search

import org.springframework.data.jpa.domain.Specification
import eco.omsk.report.assistant.service.search.form.RequestFilterForm

abstract class EntityMultiFilter<T>(private val filters: List<EntityFilter<T>>) : EntityFilter<T>() {

    override fun buildSpecification(form: RequestFilterForm): Specification<T> {
        return filters
                .map { it.buildSpecification(form) }
                .reduce(foldSpecifications())
    }

    protected abstract fun foldSpecifications(): (Specification<T>, Specification<T>) -> Specification<T>
}