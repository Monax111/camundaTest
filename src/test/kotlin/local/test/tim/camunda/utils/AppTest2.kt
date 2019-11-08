package local.test.tim.camunda.utils

import local.test.tim.camunda.delegate.LoggingDelegate
import local.test.tim.camunda.endpoint.ProcessController
import local.test.tim.camunda.service.CamundaService
import local.test.tim.camunda.variables.MessageName
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
@ComponentScan(basePackages = ["org.camunda.bpm"])
class AppTest2 {

    @Autowired
    lateinit var runtimeService : RuntimeService

    @Autowired
    lateinit var camundaService : CamundaService

    @Autowired
    lateinit var processController : ProcessController

    @Autowired
    lateinit var loggingDelegate : LoggingDelegate


    @Test
    fun checkSome() {
        val appId = "123123213"
        processController.startProcess("loanApproval", appId)
        val process = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(appId).list().first()
        assertThat(process).isWaitingAt("WaitMassege")
        processController.sendMesage(MessageName.FIRST, appId)
        print("До сна")
        Thread.sleep(2000)
        print("После сна")
        assertThat(process).isEnded
    }

    private fun createProcess(appId: String):ProcessInstance = runtimeService
        .startProcessInstanceByKey(
            "loanApproval", appId
        )
}
