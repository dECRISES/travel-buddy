package org.dfm.travel.buddy.boot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.dfm.travel.buddy.domain.TravelBuddyDomain
import org.dfm.travel.buddy.domain.port.ObtainTravelBuddy
import org.dfm.travel.buddy.domain.port.RequestTravelBuddy
import org.dfm.travel.buddy.repository.config.JpaAdapterConfig

@Configuration
@Import(JpaAdapterConfig::class)
class BootstrapConfig {

  @Bean
  fun getRequestTravelBuddy(obtainTravelBuddy: ObtainTravelBuddy): RequestTravelBuddy {
    return TravelBuddyDomain(obtainTravelBuddy)
  }
}
