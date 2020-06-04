package eco.omsk.report.assistant.service.search.order

import eco.omsk.report.assistant.service.search.form.RequestOrderingForm
import org.springframework.data.domain.Sort

abstract class EntityMultiOrdering<T>(private val orderings: List<EntityOrdering<T>>) : EntityOrdering<T>() {

    override fun getSorting(forms: List<RequestOrderingForm>): Sort {
        val ordering = orderings
                .map { order -> forms.map { order.buildOrdering(it) } }
                .flatten()
                .map { Sort.by(it) }

        return when {
            ordering.isEmpty() -> return Sort.unsorted()
            else -> ordering.reduce(Sort::and)
        }
    }

}