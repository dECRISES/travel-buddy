package org.dfm.travel.buddy.rest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.dfm.travel.buddy.domain.port.RequestTravelBuddy

@SpringBootApplication
@ComponentScan(basePackages = ["org.dfm.travel.buddy"])
class TravelBuddyPoetryRestAdapterApplication {

  @MockBean
  private lateinit var requestTravelBuddy: RequestTravelBuddy
}

fun main(args: Array<String>) {
  SpringApplication.run(TravelBuddyPoetryRestAdapterApplication::class.java, *args)
}
