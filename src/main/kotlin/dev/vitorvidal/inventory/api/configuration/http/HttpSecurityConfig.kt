package dev.vitorvidal.inventory.api.configuration.http

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class HttpSecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .disable()
        http.csrf().disable()
        return http.build()
    }
}