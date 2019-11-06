package local.test.tim.camunda.utils

import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration

class ProcessTestConfig {
    companion object {
        val processEngine = StandaloneInMemProcessEngineConfiguration
            .createStandaloneInMemProcessEngineConfiguration()
            .buildProcessEngine()
    }
}
