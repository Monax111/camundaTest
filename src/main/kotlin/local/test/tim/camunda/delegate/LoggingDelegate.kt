package local.test.tim.camunda.delegate

import local.test.tim.camunda.service.CamundaService
import mu.KotlinLogging
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
open class LoggingDelegate(private val camundaService: CamundaService): JavaDelegate{
    override fun execute(execution: DelegateExecution) {
        log.info { "Call" }
        camundaService.startProcessByProcessKey("loanApproval", "55555")
        log.info { "Call LoggingDelegate on instance with businessKey = ${execution.businessKey}" }

    }

}