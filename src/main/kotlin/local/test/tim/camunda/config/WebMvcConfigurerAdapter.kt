package local.test.tim.camunda.config

import local.test.tim.camunda.App
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@EnableSwagger2
@Configuration
open class WebMvcConfigurerAdapter : WebMvcConfigurer {

    @Bean
    open fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis( RequestHandlerSelectors.basePackage(
                        App::class.java.getPackage().name
                ))
                .paths(PathSelectors.any())
                .build()
    }
}