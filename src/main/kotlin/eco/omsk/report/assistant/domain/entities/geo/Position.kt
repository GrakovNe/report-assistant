package eco.omsk.report.assistant.domain.entities.geo

import eco.omsk.report.assistant.domain.entities.BaseEntity
import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class Position : BaseEntity() {

    var latitude: BigDecimal? = null
    var longitude: BigDecimal? = null
}