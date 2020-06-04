package eco.omsk.report.assistant.service.search.order

abstract class EntitySimpleOrdering<T> {

    abstract fun getOrderingFields(): List<String>
}