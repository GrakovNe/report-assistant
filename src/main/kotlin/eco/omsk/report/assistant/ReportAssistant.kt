package eco.omsk.report.assistant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class ReportAssistant

fun main(args: Array<String>) {
    runApplication<ReportAssistant>(*args)
}
