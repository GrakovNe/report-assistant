package eco.omsk.report.assistant.service.search

import eco.omsk.report.assistant.api.rest.converter.RequestFilterConverter
import eco.omsk.report.assistant.api.rest.converter.RequestOrderingConverter
import eco.omsk.report.assistant.domain.entity.BaseEntity
import eco.omsk.report.assistant.service.search.form.RequestFilterForm
import eco.omsk.report.assistant.service.search.form.RequestOrderingForm
import eco.omsk.report.assistant.service.search.order.EntityOrdering
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import java.util.*

abstract class BaseSearchService<Entity : BaseEntity>(
        private val filters: List<EntityFilter<Entity>>,
        private val orderings: List<EntityOrdering<Entity>>) {

    private val defaultOrderingField = "id"

    open fun findFilters(forms: List<RequestFilterForm>): Specification<Entity>? = filters
            .map { it.getSpecification(forms) }
            .filter(Objects::nonNull)
            .ifEmpty { listOf(findDefaultFilter()) }
            .reduce(Specification<Entity>::and)

    protected open fun findDefaultFilter(): Specification<Entity> = Specification { _, _, builder -> builder.and() }

    open fun findOrdering(forms: List<RequestOrderingForm>): Sort {
        return orderings
                .map { it.getSorting(forms) }
                .filter(Objects::nonNull)
                .filter { it.isSorted }
                .ifEmpty { listOf(findDefaultOrdering()) }
                .reduce(Sort::and)
    }

    protected open fun findDefaultOrdering(): Sort = Sort.by(Sort.Direction.DESC, defaultOrderingField)
}