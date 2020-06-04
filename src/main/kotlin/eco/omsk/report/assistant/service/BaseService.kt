package eco.omsk.report.assistant.service

import eco.omsk.report.assistant.domain.entity.BaseEntity
import eco.omsk.report.assistant.domain.repository.BaseRepository
import eco.omsk.report.assistant.exception.ReportAssistantException
import eco.omsk.report.assistant.service.search.BaseSearchService
import eco.omsk.report.assistant.service.search.form.RequestFilterForm
import eco.omsk.report.assistant.service.search.form.RequestOrderingForm
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

open class BaseService<Entity : BaseEntity>(private val repository: BaseRepository<Entity>,
                                            private val searchService: BaseSearchService<Entity>) {

    fun search(filters: List<RequestFilterForm>, orderings: List<RequestOrderingForm>, page: Int, size: Int): Page<Entity> =
            repository.findAll(searchService.findFilters(filters), PageRequest.of(page, size, searchService.findOrdering(orderings)))

    fun requireEntity(id: Long): Entity = repository
            .findById(id)
            .orElseThrow { ReportAssistantException("Unable to found requested entity with id $id") }
}