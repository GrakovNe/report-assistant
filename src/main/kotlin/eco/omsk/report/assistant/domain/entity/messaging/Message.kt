package eco.omsk.report.assistant.domain.entity.messaging

import eco.omsk.report.assistant.domain.entity.BaseEntity
import eco.omsk.report.assistant.domain.entity.auth.Reporter
import eco.omsk.report.assistant.domain.entity.auth.User
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Message : BaseEntity() {
    var text: String? = null

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    var dialog: Dialog? = null
}