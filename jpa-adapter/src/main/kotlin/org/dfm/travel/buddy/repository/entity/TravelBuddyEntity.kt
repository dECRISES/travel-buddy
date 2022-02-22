package org.dfm.travel.buddy.repository.entity

import org.hibernate.envers.Audited
import org.dfm.travel.buddy.domain.model.TravelBuddy
import javax.persistence.*

@Table(name = "T_TRAVEL_BUDDY")
@Entity
@SequenceGenerator(name = "SEQ_T_TRAVEL_BUDDY", initialValue = 1, allocationSize = 100)
@Audited
data class TravelBuddyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_TRAVEL_BUDDY")
    @SequenceGenerator(name = "SEQ_T_TRAVEL_BUDDY", sequenceName = "SEQ_T_TRAVEL_BUDDY", allocationSize = 1, initialValue = 1)
    @Column(name = "TECH_ID")
    private val techId: Long? = null,
    @Column(name = "CODE")
    private val code: Long? = null,
    @Column(name = "DESCRIPTION")
    private val description: String) {
  fun toModel(): TravelBuddy {
    return TravelBuddy(code, description)
  }
}
