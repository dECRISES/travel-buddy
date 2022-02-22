package org.dfm.travel.buddy.rest.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.dfm.travel.buddy.domain.exception.TravelBuddyNotFoundException
import java.lang.Exception

@RestControllerAdvice(basePackages = ["org.dfm.travel.buddy"])
class TravelBuddyExceptionHandler {

  @ExceptionHandler(value = [TravelBuddyNotFoundException::class])
  fun handleTravelBuddyNotFoundException(exception: Exception, request: WebRequest): ResponseEntity<TravelBuddyExceptionResponse> {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TravelBuddyExceptionResponse(exception.message, (request as ServletWebRequest).request.requestURI))
  }
}
