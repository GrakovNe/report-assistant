package eco.omsk.report.assistant.service.search.order

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.beans.factory.support.RootBeanDefinition
import org.springframework.core.ResolvableType
import ru.rbru.pmp.shared.order.EntityDynamicOrdering
import java.util.function.Supplier

abstract class DynamicOrderingBeanPostProcessor<T> : BeanDefinitionRegistryPostProcessor {

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        // do nothing
    }

    override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry) {
        val defaultListableBeanFactory = (registry as DefaultListableBeanFactory)
        val orderings: List<EntitySimpleOrdering<T>> = findSimpleOrderings(defaultListableBeanFactory)
        registerOrderings(orderings, registry)
    }

    @Suppress("UNCHECKED_CAST")
    private fun findSimpleOrderings(defaultListableBeanFactory: DefaultListableBeanFactory): List<EntitySimpleOrdering<T>> {
        return defaultListableBeanFactory
                .getBeanNamesForType(ResolvableType.forClassWithGenerics(EntitySimpleOrdering::class.java, getEntityClass()))
                .map { defaultListableBeanFactory.getBean(it) } as List<EntitySimpleOrdering<T>>
    }

    private fun registerOrderings(orderings: List<EntitySimpleOrdering<T>>, registry: BeanDefinitionRegistry) {
        orderings.map { it.getOrderingFields() }
                .flatten()
                .forEach {
                    val name = buildOrderingName(it)

                    val rootBeanDefinition = RootBeanDefinition(EntityDynamicOrdering::class.java, Supplier<EntityDynamicOrdering> { EntityDynamicOrdering(it) })

                    rootBeanDefinition.setTargetType(ResolvableType.forClassWithGenerics(EntityOrdering::class.java, getEntityClass()))
                    registry.registerBeanDefinition(name, rootBeanDefinition)
                }
    }

    private fun buildOrderingName(it: String) = "${getEntityClass().simpleName.decapitalize()}SimpleOrdering${it.capitalize()}"

    abstract fun getEntityClass(): Class<T>
}