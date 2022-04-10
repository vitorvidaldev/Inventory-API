package dev.vitorvidal.inventory.api.config

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