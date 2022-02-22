package org.dfm.travel.buddy.domain.exception

class TravelBuddyNotFoundException(id: Long) : RuntimeException("TravelBuddy with code: [$id] does not exists")

