package eco.omsk.report.assistant.domain.entity.auth

import eco.omsk.report.assistant.domain.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.MappedSuperclass

@MappedSuperclass
class User : BaseEntity() {
    var name: String? = null
}