package eco.omsk.report.assistant.domain.entities.auth

import eco.omsk.report.assistant.domain.entities.BaseEntity
import javax.persistence.MappedSuperclass

@MappedSuperclass
class User : BaseEntity() {
    var name: String? = null
}