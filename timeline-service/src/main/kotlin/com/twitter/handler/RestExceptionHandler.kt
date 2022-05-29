package com.twitter.handler

import com.twitter.exception.ExceptionDetails
import com.twitter.exception.TimelineNotFound
import io.micrometer.core.lang.Nullable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(TimelineNotFound::class)])
    fun handleTimelineNotFound(ex: TimelineNotFound): ResponseEntity<Any> = createResponse(
        ExceptionDetails(
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.NOT_FOUND.value(),
            title = "Resource not Found",
            details = ex.message ?: "",
            developerMethod = ex.javaClass.name
        ), HttpStatus.NOT_FOUND, null
    )

    @ExceptionHandler
    override fun handleExceptionInternal(
        ex: Exception,
        @Nullable body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> = createResponse(
        ExceptionDetails(
            timestamp = LocalDateTime.now().toString(),
            status = status.value(),
            title = ex.cause?.message ?: "",
            details = ex.message ?: "",
            developerMethod = ex.javaClass.name
        ), status, headers
    )

    private fun createResponse(
        details: ExceptionDetails,
        status: HttpStatus,
        headers: HttpHeaders?
    ): ResponseEntity<Any> {
        log.error(details.details)
        return ResponseEntity<Any>(details, headers, status)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

}