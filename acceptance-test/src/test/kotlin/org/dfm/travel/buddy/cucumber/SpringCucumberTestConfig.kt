package org.dfm.travel.buddy.cucumber

import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.dfm.travel.buddy.boot.TravelBuddyApplication

@SpringBootTest(classes = [TravelBuddyApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@ActiveProfiles("test")
class SpringCucumberTestConfig
