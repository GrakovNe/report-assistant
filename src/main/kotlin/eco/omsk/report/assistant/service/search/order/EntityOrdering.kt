package eco.omsk.report.assistant.service.search.order

import eco.omsk.report.assistant.service.search.form.RequestOrderingDirection
import eco.omsk.report.assistant.service.search.form.RequestOrderingForm
import org.springframework.data.domain.Sort

abstract class EntityOrdering<T> {

    abstract val name: String
    open val field: String = name


    open fun getDirections(): List<RequestOrderingDirection> {
        return RequestOrderingDirection.values().asList()
    }

    open fun getSorting(forms: List<RequestOrderingForm>): Sort {
        val orderings = forms
                .filter { it.field == name }
                .map { buildOrdering(it) }
                .toList()

        return Sort.by(orderings)
    }

    open fun buildOrdering(form: RequestOrderingForm): Sort.Order {
        return createOrder(field = field, direction = form.direction)
    }

    private fun createOrder(field: String, direction: RequestOrderingDirection): Sort.Order {
        val orderingDirection = when (direction) {
            RequestOrderingDirection.ASCENDING -> Sort.Direction.ASC
            RequestOrderingDirection.DESCENDING -> Sort.Direction.DESC
        }

        return Sort
                .Order(orderingDirection, field, Sort.NullHandling.NULLS_FIRST)
                .ignoreCase()
    }
}