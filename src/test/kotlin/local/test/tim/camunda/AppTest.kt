package local.test.tim.camunda

import local.test.tim.camunda.endpoint.ProcessController
import local.test.tim.camunda.service.CamundaService
import local.test.tim.camunda.utils.ProcessTestConfig
import local.test.tim.camunda.variables.MessageName
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService
import org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest
import org.junit.Before
import org.junit.Test

@Deployment(resources = ["loanApproval.bpmn"])
class AppTest: AbstractProcessEngineRuleTest() {

    val rs = runtimeService()
    init {
            processEngine.runtimeService = runtimeService()
        }



    private val camundaService = CamundaService(rs)
    val processController = ProcessController(camundaService)


    @Test
    fun checkSome() {
        processController.startProcess("loanApproval","123123213")
        val process =  rs.createProcessInstanceQuery()
                .processDefinitionKey("loanApproval")
                .processInstanceBusinessKey("123123213")
                .list().first()
        assertThat(process).isWaitingAt("WaitMassege")
        processController.sendMesage(MessageName.FIRST, "123123213")
        assertThat(process).isWaitingAt("DoSome")
        processEngine.managementService.executeJob("DoSome")
        assertThat(process).isWaitingAt("DoSome2")
    }
}


