package org.dfm.travel.buddy.domain

import org.dfm.travel.buddy.domain.exception.TravelBuddyNotFoundException
import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.model.TravelBuddyInfo
import org.dfm.travel.buddy.domain.port.ObtainTravelBuddy
import org.dfm.travel.buddy.domain.port.RequestTravelBuddy

class TravelBuddyDomain(private val obtainTravelBuddy: ObtainTravelBuddy) : RequestTravelBuddy {

  constructor() : this(object : ObtainTravelBuddy {})

  override fun getTravelBuddies(): TravelBuddyInfo {
    return TravelBuddyInfo(obtainTravelBuddy.getAllTravelBuddies())
  }

  override fun getTravelBuddyByCode(code: Long): TravelBuddy {
    val travelBuddy = obtainTravelBuddy.getTravelBuddyByCode(code)
    return when(travelBuddy.isPresent) {
      true -> travelBuddy.get()
      false -> throw TravelBuddyNotFoundException(code)
    }
  }
}
