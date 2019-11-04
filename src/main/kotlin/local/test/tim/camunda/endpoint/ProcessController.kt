package local.test.tim.camunda.endpoint

import local.test.tim.camunda.service.CamundaService
import local.test.tim.camunda.variables.MessageName
import org.camunda.bpm.engine.runtime.ProcessInstance
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("process")
class ProcessController(
        private  val camundaService: CamundaService
) {
    @GetMapping("/start/processKey/{processKey}/businessKey/{businessKey}")
    fun getBook(@PathVariable processKey: String, @PathVariable businessKey: String): String =
        camundaService.startProcessByProcessKey(processKey = processKey, businessKey = businessKey).id
    //TODO ошибка в десереализации инстанса! ПОчему то нельзя вывести ProcessInstance

    @PutMapping("/sendMessage/name/{name}/businessKey/{businessKey}")
    fun sendMesage(@PathVariable name:MessageName, @PathVariable businessKey: String ){
        camundaService.sendMesage(name, businessKey)
    }
}
