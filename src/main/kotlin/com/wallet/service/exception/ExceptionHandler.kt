package com.wallet.service.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders, status:
                                              HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        //Fixme add norm error format
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error")
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException::class)
    fun handleNotFound(ex: RuntimeException): ResponseEntity<Any> {
        //Fixme add norm error format
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }
}