package org.dfm.travel.buddy.repository.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.history.RevisionRepository
import org.springframework.stereotype.Repository
import org.dfm.travel.buddy.repository.entity.TravelBuddyEntity
import java.util.*

@Repository
interface TravelBuddyDao : JpaRepository<TravelBuddyEntity, Long>, RevisionRepository<TravelBuddyEntity, Long, Long>  {
  fun findByCode(code: Long): Optional<TravelBuddyEntity>
}
