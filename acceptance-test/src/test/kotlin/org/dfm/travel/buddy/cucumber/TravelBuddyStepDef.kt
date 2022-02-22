package org.dfm.travel.buddy.cucumber

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import io.cucumber.spring.CucumberContextConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.dfm.travel.buddy.boot.TravelBuddyApplication
import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.model.TravelBuddyInfo
import org.dfm.travel.buddy.repository.dao.TravelBuddyDao
import org.dfm.travel.buddy.repository.entity.TravelBuddyEntity
import org.dfm.travel.buddy.rest.exception.TravelBuddyExceptionResponse


class TravelBuddyStepDef(restTemplate: TestRestTemplate, travelBuddyDao: TravelBuddyDao) : En {

  companion object {
    private const val LOCALHOST = "http://localhost:"
    private const val API_URI = "/api/v1/travelBuddies"
  }

  @LocalServerPort
  private val port: Int = 0
  private lateinit var responseEntity: ResponseEntity<*>

  init {

    DataTableType { row: Map<String, String> -> TravelBuddy(row["code"].toString().toLong(), row["description"].toString()) }
    DataTableType { row: Map<String, String> -> TravelBuddyEntity(code = row["code"].toString().toLong(), description = row["description"].toString()) }

    Before { _ -> travelBuddyDao.deleteAll() }
    After { _ -> travelBuddyDao.deleteAll() }

    Given("the following travelBuddies exists in the library") { dataTable: DataTable ->
      val travelBuddies = dataTable.asList<TravelBuddyEntity>(TravelBuddyEntity::class.java)
      travelBuddyDao.saveAll(travelBuddies)
    }

    When("user requests for all travelBuddies") {
      val url = "$LOCALHOST$port$API_URI"
      responseEntity = restTemplate.getForEntity(url, TravelBuddyInfo::class.java)
    }

    When("user requests for travelBuddies by code {string}") { code: String? ->
      val url = "$LOCALHOST$port$API_URI/$code"
      responseEntity = restTemplate.getForEntity(url, TravelBuddy::class.java)
    }

    When("user requests for travelBuddies by id {string} that does not exists") { id: String? ->
      val url = "$LOCALHOST$port$API_URI/$id"
      responseEntity = restTemplate.getForEntity(url, TravelBuddyExceptionResponse::class.java)
    }

    Then("the user gets an exception {string}") { exception: String? ->
      assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
      val body = responseEntity.body
      assertThat(body).isInstanceOf(TravelBuddyExceptionResponse::class.java)
      when (body) {
        is TravelBuddyExceptionResponse -> assertThat(body.message).isEqualTo(exception)
      }
    }

    Then("the user gets the following travelBuddies") { dataTable: DataTable ->
      val expectedTravelBuddies = dataTable.asList<TravelBuddy>(TravelBuddy::class.java)
      assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
      val body = responseEntity.body
      assertThat(body).isNotNull
      when (body) {
        is TravelBuddyInfo -> assertThat(body.travelBuddies).isNotEmpty.extracting("description").containsAll(expectedTravelBuddies.map { it.description })
        is TravelBuddy -> assertThat(body).isNotNull.extracting("description").isEqualTo(expectedTravelBuddies.first().description)
      }
    }
  }
}


