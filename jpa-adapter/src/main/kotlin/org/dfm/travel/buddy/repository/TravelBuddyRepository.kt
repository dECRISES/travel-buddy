package org.dfm.travel.buddy.repository

import org.dfm.travel.buddy.domain.model.TravelBuddy
import org.dfm.travel.buddy.domain.port.ObtainTravelBuddy
import org.dfm.travel.buddy.repository.dao.TravelBuddyDao
import java.util.*

class TravelBuddyRepository(private val travelBuddyDao: TravelBuddyDao) : ObtainTravelBuddy {

  override fun getAllTravelBuddies(): List<TravelBuddy> {
    return travelBuddyDao.findAll().map { it.toModel() }
  }

  override fun getTravelBuddyByCode(code: Long): Optional<TravelBuddy> {
    val travelBuddy = travelBuddyDao.findByCode(code)
    return when(travelBuddy.isPresent) {
      true -> Optional.of(travelBuddy.get().toModel())
      false -> Optional.empty()
    }
  }
}
