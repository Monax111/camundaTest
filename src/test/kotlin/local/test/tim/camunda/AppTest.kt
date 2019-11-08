package local.test.tim.camunda

import local.test.tim.camunda.utils.ProcessTestConfig
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat
import org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock
import org.junit.Rule
import org.junit.Test

@Deployment(resources = ["loanApproval.bpmn"])
class AppTest {

    @get:Rule
    var processEngineRule = ProcessEngineRule(ProcessTestConfig.processEngine)

    @Test
    fun checkSome() {
        autoMock("loanApproval.bpmn")
        //processController.startProcess("loanApproval", "123")
        val process = createProcess("123123213")
        assertThat(process).isWaitingAt("WaitMassege")
    }

    private fun createProcess(appId: String):ProcessInstance = processEngineRule.runtimeService
        .startProcessInstanceByKey(
            "loanApproval", appId
        )
}
