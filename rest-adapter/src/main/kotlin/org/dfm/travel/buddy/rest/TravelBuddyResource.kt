package org.dfm.travel.buddy.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.model.TravelBuddyInfo
import org.dfm.travel.buddy.domain.port.RequestTravelBuddy

@RestController
@RequestMapping("/api/v1/travelBuddies")
class TravelBuddyResource(private val requestTravelBuddy: RequestTravelBuddy) {

  @GetMapping
  fun getTravelBuddies(): ResponseEntity<TravelBuddyInfo> {
    return ResponseEntity.ok(requestTravelBuddy.getTravelBuddies())
  }

  @GetMapping(path = ["/{code}"])
  fun getTravelBuddyByCode(@PathVariable code: Long): ResponseEntity<TravelBuddy> {
    return ResponseEntity.ok(requestTravelBuddy.getTravelBuddyByCode(code))
  }
}
