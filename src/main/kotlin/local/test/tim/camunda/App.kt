package local.test.tim.camunda

import mu.KotlinLogging
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.web.servlet.config.annotation.EnableWebMvc

private val log = KotlinLogging.logger {}

@SpringBootApplication
@EnableProcessApplication
open class App(
        private val runtimeService: RuntimeService
) {

 
    
    
    @EventListener
    fun start(event: PostDeployEvent) {
        log.info { "startProcessInstanceByKey : loanApproval " }
        runtimeService.startProcessInstanceByKey("loanApproval", "123")


    }
}


fun main(args: Array<String>) {
    runApplication<App>(args = *args)
}

