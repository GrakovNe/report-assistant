package eco.omsk.report.assistant.service.search

import org.springframework.data.jpa.domain.Specification
import eco.omsk.report.assistant.service.search.form.RequestFilterForm

/**
 * Know how to filter entity by custom field with available values and type.
 *
 * To Implement another one filter for entity, just create another one implementation of this and register it as Spring Bean.
 *
 * See: PaymentTypeFilterProvider for example
 */
abstract class EntityFilter<T> {

    abstract fun getType(): FilterType;

    open fun getValues(): List<*>? {
        return null;
    }

    open fun getMinimalValue(): String? {
        return null;
    }

    open fun getMaximalValue(): String? {
        return null;
    }

    abstract fun getName(): String

    abstract fun buildSpecification(form: RequestFilterForm): Specification<T>

    fun getSpecification(forms: List<RequestFilterForm>): Specification<T> {
        return forms
                .filter { it.field == getName() }.map { buildSpecification(it) }
                .stream()
                .reduce(Specification<T>::or)
                .orElse(Specification.where { _, _, criteriaBuilder -> criteriaBuilder.and() })
    }
}