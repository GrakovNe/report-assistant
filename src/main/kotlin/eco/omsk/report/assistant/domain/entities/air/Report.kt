package eco.omsk.report.assistant.domain.entities.air

import eco.omsk.report.assistant.domain.SmellIntensity
import eco.omsk.report.assistant.domain.SmellType
import eco.omsk.report.assistant.domain.entities.BaseEntity
import eco.omsk.report.assistant.domain.entities.geo.Position
import eco.omsk.report.assistant.domain.entities.auth.Reporter
import javax.persistence.Entity

@Entity
class Report : BaseEntity() {
    var reporter: Reporter? = null
    var position: Position? = null
    var intensity: SmellIntensity? = null
    var type: SmellType? = null
}