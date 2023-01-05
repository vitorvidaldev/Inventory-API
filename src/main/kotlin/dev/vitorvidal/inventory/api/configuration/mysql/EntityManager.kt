package dev.vitorvidal.inventory.api.configuration.mysql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class EntityManager {
    @Value("\${datasource-url}")
    lateinit var url: String

    @Value("\${datasource-username}")
    lateinit var username: String

    @Value("\${datasource-password}")
    lateinit var password: String

    @Value("\${datasource-driver-class-name}")
    lateinit var driveClassName: String

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.driverClassName = driveClassName
        config.jdbcUrl = url
        config.username = username
        config.password = password
        config.maximumPoolSize = 1000

        return HikariDataSource(config)
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        val transactionManagement = JpaTransactionManager()
        return transactionManagement
    }


}