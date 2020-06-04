package ru.rbru.pmp.shared.order

import eco.omsk.report.assistant.service.search.order.EntityOrdering

class EntityDynamicOrdering(private val ordering: String) : EntityOrdering<Any>() {

    override val name: String = ordering
}