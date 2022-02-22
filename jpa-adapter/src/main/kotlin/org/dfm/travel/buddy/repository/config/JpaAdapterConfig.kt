package org.dfm.travel.buddy.repository.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.dfm.travel.buddy.domain.port.ObtainTravelBuddy
import org.dfm.travel.buddy.repository.TravelBuddyRepository
import org.dfm.travel.buddy.repository.dao.TravelBuddyDao

@Configuration
@EntityScan("org.dfm.travel.buddy.repository.entity")
@EnableJpaRepositories("org.dfm.travel.buddy.repository.dao", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean::class)
class JpaAdapterConfig {

  @Bean
  fun getTravelBuddyRepository(travelBuddyDao: TravelBuddyDao): ObtainTravelBuddy {
    return TravelBuddyRepository(travelBuddyDao)
  }
}
