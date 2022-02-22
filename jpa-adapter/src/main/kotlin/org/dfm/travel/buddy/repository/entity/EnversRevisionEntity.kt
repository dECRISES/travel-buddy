package org.dfm.travel.buddy.repository.entity

import javax.persistence.*

@Table(name = "REVISION_INFO", schema = "TRAVEL_BUDDY_AUDIT")
@Entity
@SequenceGenerator(
    schema = "TRAVEL_BUDDY_AUDIT", name = "SEQ_REVISION_INFO",
    sequenceName = "TRAVEL_BUDDY_AUDIT.SEQ_REVISION_INFO", allocationSize = 1
)
data class EnversRevisionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVISION_INFO")
    @Column(name = "REV")
    private val rev: Long? = null,
    @Column(name = "TIMESTAMP")
    private val code: Long? = null
)