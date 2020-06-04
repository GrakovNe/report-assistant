package eco.omsk.report.assistant.domain.entity.messaging

import eco.omsk.report.assistant.domain.entity.BaseEntity
import eco.omsk.report.assistant.domain.entity.DialogState
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Dialog : BaseEntity() {

    var state: DialogState? = null

    @OneToMany(mappedBy = "dialog")
    var messages: MutableList<Message> = mutableListOf()
}