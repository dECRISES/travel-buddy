package org.dfm.travel.buddy.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["org.dfm.travel.buddy"])
class TravelBuddyApplication

fun main() {
  SpringApplication.run(TravelBuddyApplication::class.java)
}
