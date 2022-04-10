package dev.vitorvidal.inventory.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerConfig : WebMvcConfigurer {
    // TODO
//
//    @Bean
//    fun OpenAPI(): OpenAPI {
//        return OpenAPI().info(
//            Info()
//                .title("Inventory API")
//                .description("Inventory Management Application")
//                .contact(Contact().name("Vitor Vidal"))
//                .version("v1.0")
//                .license(License().name("MIT License"))
//        )
//    }
}