package com.twitter.handler

import com.twitter.exception.ExceptionDetails
import com.twitter.exception.TimelineNotFound
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime


@Slf4j
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(TimelineNotFound::class)])
    fun handleTimelineNotFound(ex: TimelineNotFound): ResponseEntity<ExceptionDetails> {
        val resourceNotFoundDetails = ExceptionDetails(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            title = "Resource not Found",
            details = ex.message,
            developerMethod = ex.javaClass.name
        )
        return ResponseEntity<ExceptionDetails>(resourceNotFoundDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    override fun handleExceptionInternal(
        ex: Exception,
        @Nullable body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val exceptionDetails = ExceptionDetails(
            timestamp = LocalDateTime.now(),
            status = status.value(),
            title = ex.cause!!.message,
            details = ex.message,
            developerMethod = ex.javaClass.name
        )
        return ResponseEntity(exceptionDetails, headers, status)
    }

}