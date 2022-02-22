package org.dfm.travel.buddy.repository

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import
import org.dfm.travel.buddy.repository.config.JpaAdapterConfig

@SpringBootApplication
class TravelBuddyJpaAdapterApplication {

    fun main(args: Array<String>) {
        SpringApplication.run(TravelBuddyJpaAdapterApplication::class.java)
    }

    @TestConfiguration
    @Import(JpaAdapterConfig::class)
    class TravelBuddyJpaTestConfig
}
