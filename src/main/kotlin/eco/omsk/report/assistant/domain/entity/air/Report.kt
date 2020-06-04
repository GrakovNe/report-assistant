package eco.omsk.report.assistant.domain.entity.air

import eco.omsk.report.assistant.domain.SmellType
import eco.omsk.report.assistant.domain.entity.BaseEntity
import eco.omsk.report.assistant.domain.entity.auth.Reporter
import eco.omsk.report.assistant.domain.entity.geo.Position
import javax.persistence.*

@Entity
class Report : BaseEntity() {
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    var reporter: Reporter? = null

    @ManyToOne
    @JoinColumn(name = "position_id")
    var position: Position? = null

    var intensity: Int? = null

    @Enumerated(value = EnumType.STRING)
    var type: SmellType? = null
    var comment: String? = null
}