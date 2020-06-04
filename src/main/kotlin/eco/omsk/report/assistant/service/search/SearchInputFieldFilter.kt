package eco.omsk.report.assistant.service.search

import org.springframework.data.jpa.domain.Specification
import eco.omsk.report.assistant.service.search.form.RequestFilterForm
import javax.persistence.criteria.Path
import javax.persistence.criteria.Root

abstract class SearchInputFieldFilter<T> : EntityFilter<T>() {

    abstract fun accessField(): (Root<T>) -> Path<String>

    override fun getType(): FilterType {
        return FilterType.SEARCH_INPUT
    }

    override fun buildSpecification(form: RequestFilterForm): Specification<T> {
        return buildSpecification(form, accessField())
    }

    private fun buildSpecification(form: RequestFilterForm, accessor: (Root<T>) -> Path<String>): Specification<T> {
        if (form.exactlyValue != null) {
            return Specification { root, _, builder ->
                builder.equal(accessor.invoke(root), form.exactlyValue)
            }
        }

        if (form.startingWithValue != null) {
            return Specification { root, _, builder ->
                builder.like(accessor.invoke(root), "${form.startingWithValue}%")
            }
        }

        if (form.endingWithValue != null) {
            return Specification { root, _, builder ->
                builder.like(accessor.invoke(root), "%${form.endingWithValue}")
            }
        }

        return Specification { root, _, builder ->
            builder.like(accessor.invoke(root), "%${form.likeValue}%")
        }
    }
}