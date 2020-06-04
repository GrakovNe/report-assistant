package eco.omsk.report.assistant.domain.entities.messaging

import eco.omsk.report.assistant.domain.entities.BaseEntity
import eco.omsk.report.assistant.domain.entities.DialogState
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Dialog : BaseEntity() {

    var state: DialogState? = null

    @OneToMany(mappedBy = "dialog")
    var messages: MutableList<Message> = mutableListOf()
}