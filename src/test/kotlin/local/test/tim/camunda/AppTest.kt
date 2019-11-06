package local.test.tim.camunda

import local.test.tim.camunda.endpoint.ProcessController
import local.test.tim.camunda.service.CamundaService
import local.test.tim.camunda.utils.ProcessTestConfig
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareAssertions.assertThat
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.Test


class AppTest {

    @get:Rule
    var processEngineRule = ProcessEngineRule(ProcessTestConfig.processEngine)

    var camundaService = CamundaService(AbstractAssertions
        .processEngine().runtimeService)

    var processController = ProcessController(camundaService)

    @Test
    @Deployment(resources = ["loanApproval.bpmn"])
    fun checkSome() {
        //processController.startProcess("loanApproval", "123")
        val process = createProcess("123")
        assertThat(process).isWaitingAt("WaitMassege")
    }
}


private fun createProcess(appId: String):ProcessInstance = AbstractAssertions
    .processEngine().runtimeService
    .startProcessInstanceByKey(
        "loanApproval", appId
    )