package org.dfm.travel.buddy.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.port.ObtainTravelBuddy

@ExtendWith(SpringExtension::class)
@DataJpaTest
@ActiveProfiles("test")
class TravelBuddyJpaTest {

  @Autowired
  private lateinit var obtainTravelBuddy: ObtainTravelBuddy

  @Test
  fun `should start the application`() {
    assertThat(java.lang.Boolean.TRUE).isTrue
  }

  @Sql(scripts = ["/sql/data.sql"])
  @Test
  fun `given travelBuddies exists in database when asked for travelBuddies from database should return all travelBuddies`() {
    // Given from @Sql
    // When
    val travelBuddies = obtainTravelBuddy.getAllTravelBuddies()
    // Then
    assertThat(travelBuddies).isNotNull.extracting("description").contains("Twinkle twinkle little star")
  }

  @Test
  fun `given no travelBuddies exists in database when asked for travelBuddies should return empty`() {
    // When
    val travelBuddies = obtainTravelBuddy.getAllTravelBuddies()
    // Then
    assertThat(travelBuddies).isNotNull.isEmpty()
  }

  @Sql(scripts = ["/sql/data.sql"])
  @Test
  fun `given travelBuddies exists in database when asked for travelBuddy by id should return the travelBuddy`() {
    // Given from @Sql
    // When
    val travelBuddy = obtainTravelBuddy.getTravelBuddyByCode(1L)
    // Then
    assertThat(travelBuddy).isNotNull.get().isEqualTo(TravelBuddy(1L, "Twinkle twinkle little star"))
  }

  @Sql(scripts = ["/sql/data.sql"])
  @Test
  fun `given travelBuddies exists in database when asked for travelBuddy by id that does not exists should empty`() {
    // Given from @Sql
    // When
    val travelBuddy = obtainTravelBuddy.getTravelBuddyByCode(-1000L)
    // Then
    assertThat(travelBuddy).isEmpty
  }
}
