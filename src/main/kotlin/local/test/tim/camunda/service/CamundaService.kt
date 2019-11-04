package local.test.tim.camunda.service

import local.test.tim.camunda.variables.MessageName
import mu.KotlinLogging
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class CamundaService(
        private val runtimeService: RuntimeService
) {
    fun startProcessByProcessKey(processKey: String, businessKey: String): ProcessInstance {
        log.info { "Try to start  processKey: $processKey with businessKey : $businessKey" }
        val process = runtimeService.startProcessInstanceByKey(processKey, businessKey)
        log.info { "Started Process : $process" }
        return process
    }

    fun sendMesage(name: MessageName, businessKey: String) {
        log.info { "Try to send message with name: $name with businessKey : $businessKey" }
        runtimeService.correlateMessage(name.toString(), businessKey)
        log.info { "Message sent" }
    }
}