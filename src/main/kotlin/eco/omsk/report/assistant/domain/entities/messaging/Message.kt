package eco.omsk.report.assistant.domain.entities.messaging

import eco.omsk.report.assistant.domain.entities.BaseEntity
import eco.omsk.report.assistant.domain.entities.auth.User
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Message : BaseEntity() {

    var sender: User? = null
    var text: String? = null

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    var dialog: Dialog? = null
}