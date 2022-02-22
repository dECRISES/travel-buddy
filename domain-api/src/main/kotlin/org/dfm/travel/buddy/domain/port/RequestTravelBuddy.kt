package org.dfm.travel.buddy.domain.port

import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.model.TravelBuddyInfo

interface RequestTravelBuddy {
  fun getTravelBuddies(): TravelBuddyInfo
  fun getTravelBuddyByCode(code: Long): TravelBuddy
}
