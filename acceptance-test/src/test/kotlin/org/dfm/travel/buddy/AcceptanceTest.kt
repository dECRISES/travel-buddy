package org.dfm.travel.buddy

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.dfm.travel.buddy.domain.TravelBuddyDomain
import org.dfm.travel.buddy.domain.exception.TravelBuddyNotFoundException
import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.port.ObtainTravelBuddy
import java.util.*

@ExtendWith(MockitoExtension::class)
class AcceptanceTest {

  @Test
  fun `should be able to get travelBuddies when asked for travelBuddies from hard coded travelBuddies`() {
    /*
      RequestTravelBuddy    - left side port
      TravelBuddyDomain     - hexagon (domain)
      ObtainTravelBuddy     - right side port
    */
    val requestTravelBuddy = TravelBuddyDomain() // the travelBuddy is hard coded
    val travelBuddyInfo = requestTravelBuddy.getTravelBuddies()
    assertThat(travelBuddyInfo).isNotNull
    assertThat(travelBuddyInfo.travelBuddies).isNotEmpty.extracting("description")
        .contains("If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)")
  }

  @Test
  fun `should be able to get travelBuddies when asked for travelBuddies from stub`(@Mock obtainTravelBuddy: ObtainTravelBuddy) {
    // Stub
    val travelBuddy = TravelBuddy(1L, "I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)")
    Mockito.lenient().`when`(obtainTravelBuddy.getAllTravelBuddies()).thenReturn(listOf(travelBuddy))
    // hexagon
    val requestTravelBuddy = TravelBuddyDomain(obtainTravelBuddy)
    val travelBuddyInfo = requestTravelBuddy.getTravelBuddies()
    assertThat(travelBuddyInfo).isNotNull
    assertThat(travelBuddyInfo.travelBuddies).isNotEmpty.extracting("description")
        .contains("I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)")
  }

  @Test
  fun `should be able to get travelBuddy when asked for travelBuddy by id from stub`(@Mock obtainTravelBuddy: ObtainTravelBuddy) {
    // Given
    // stub
    val code = 1L
    val description = "I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)"
    val expectedTravelBuddy = TravelBuddy(code, description)
    Mockito.lenient().`when`(obtainTravelBuddy.getTravelBuddyByCode(code)).thenReturn(Optional.of(expectedTravelBuddy))
    // When
    val requestTravelBuddy = TravelBuddyDomain(obtainTravelBuddy)
    val actualTravelBuddy = requestTravelBuddy.getTravelBuddyByCode(code)
    // Then
    assertThat(actualTravelBuddy).isNotNull.isEqualTo(expectedTravelBuddy)
  }

  @Test
  fun `should be throw exception when asked for travelBuddy by id that does not exists from stub`(@Mock obtainTravelBuddy: ObtainTravelBuddy) {
    // Given
    // stub
    val code = -1000L
    Mockito.lenient().`when`(obtainTravelBuddy.getTravelBuddyByCode(code)).thenReturn(Optional.empty())
    // When
    val requestTravelBuddy = TravelBuddyDomain(obtainTravelBuddy)
    // Then
    assertThatThrownBy { requestTravelBuddy.getTravelBuddyByCode(code) }.isInstanceOf(TravelBuddyNotFoundException::class.java)
        .hasMessageContaining("TravelBuddy with code: [$code] does not exists")
  }
}
