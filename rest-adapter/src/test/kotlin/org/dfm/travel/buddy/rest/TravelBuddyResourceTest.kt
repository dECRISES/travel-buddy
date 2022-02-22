package org.dfm.travel.buddy.rest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.dfm.travel.buddy.domain.exception.TravelBuddyNotFoundException
import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.model.TravelBuddyInfo
import org.dfm.travel.buddy.domain.port.RequestTravelBuddy
import org.dfm.travel.buddy.rest.exception.TravelBuddyExceptionResponse

@ExtendWith(MockitoExtension::class)
@SpringBootTest(classes = [TravelBuddyPoetryRestAdapterApplication::class], webEnvironment = RANDOM_PORT)
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class TravelBuddyResourceTest {
  companion object {
    private const val LOCALHOST = "http://localhost:"
    private const val API_URI = "/api/v1/travelBuddies"
  }

  @LocalServerPort
  private val port: Int = 0
  @Autowired
  private lateinit var restTemplate: TestRestTemplate
  @Autowired
  private lateinit var requestTravelBuddy: RequestTravelBuddy

  @Test
  fun `should start the rest adapter application`() {
    assertThat(java.lang.Boolean.TRUE).isTrue()
  }

  @Test
  fun `should give travelBuddies when asked for travelBuddies with the support of domain stub`() {
    // Given
    Mockito.lenient().`when`(requestTravelBuddy.getTravelBuddies()).thenReturn(mockTravelBuddyInfo())
    // When
    val url = "$LOCALHOST$port$API_URI"
    val responseEntity = restTemplate.getForEntity(url, TravelBuddyInfo::class.java)
    // Then
    assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
    assertThat(responseEntity.body).isNotNull
    assertThat(responseEntity.body.travelBuddies).isNotEmpty.extracting("description").contains("Johnny Johnny Yes Papa !!")
  }

  @Test
  fun `should give the travelBuddy when asked for an travelBuddy by id with the support of domain stub`() {
    // Given
    val code = 1L
    val description = "Twinkle Twinkle"
    Mockito.lenient().`when`(requestTravelBuddy.getTravelBuddyByCode(code)).thenReturn(mockTravelBuddy(code, description))
    // When
    val url = "$LOCALHOST$port$API_URI/$code"
    val responseEntity = restTemplate.getForEntity(url, TravelBuddy::class.java)
    // Then
    assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
    assertThat(responseEntity.body).isNotNull
    assertThat(responseEntity.body).isNotNull.isEqualTo(mockTravelBuddy(code, description))
  }

  @Test
  fun `should give exception when asked for an travelBuddy by id that does not exists with the support of domain stub`() {
    // Given
    val code = -1000L
    Mockito.lenient().`when`(requestTravelBuddy.getTravelBuddyByCode(code)).thenThrow(TravelBuddyNotFoundException(code))
    // When
    val url = "$LOCALHOST$port$API_URI/$code"
    val responseEntity = restTemplate.getForEntity(url, TravelBuddyExceptionResponse::class.java)
    // Then
    assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    assertThat(responseEntity.body).isNotNull
    assertThat(responseEntity.body).isEqualTo(TravelBuddyExceptionResponse("TravelBuddy with code: [$code] does not exists", "$API_URI/$code"))
  }

  private fun mockTravelBuddy(code: Long, description: String): TravelBuddy {
    return TravelBuddy(code, description)
  }

  private fun mockTravelBuddyInfo(): TravelBuddyInfo {
    return TravelBuddyInfo(listOf(mockTravelBuddy(1L, "Johnny Johnny Yes Papa !!")))
  }
}
