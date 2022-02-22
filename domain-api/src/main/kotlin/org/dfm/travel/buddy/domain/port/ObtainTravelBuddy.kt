package org.dfm.travel.buddy.domain.port

import org.dfm.travel.buddy.domain.model.TravelBuddy
import java.util.*

interface ObtainTravelBuddy {

  fun getAllTravelBuddies(): List<TravelBuddy> {
    val travelBuddy = TravelBuddy(1L, "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)")
    return listOf(travelBuddy)
  }

  fun getTravelBuddyByCode(code: Long): Optional<TravelBuddy> {
    return Optional.of(TravelBuddy(1L, "If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)"))
  }
}
