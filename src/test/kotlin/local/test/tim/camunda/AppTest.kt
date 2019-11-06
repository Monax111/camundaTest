package local.test.tim.camunda

import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService
import org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest
import org.junit.Test

@Deployment(resources = ["loanApproval.bpmn"])
class AppTest: AbstractProcessEngineRuleTest() {



    @Test
    fun checkSome() {
        autoMock("loanApproval.bpmn")
        //processController.startProcess("loanApproval", "123")
        val process = createProcess("123123213")
        assertThat(process).isWaitingAt("WaitMassege")
    }
}


private fun createProcess(appId: String):ProcessInstance = runtimeService()
    .startProcessInstanceByKey(
        "loanApproval", appId
    )