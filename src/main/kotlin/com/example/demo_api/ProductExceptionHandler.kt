package com.example.demo_api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ProductExceptionHandler {

    @ExceptionHandler(ProductExceptions.DuplicateProductException::class)
    fun handleDuplicate(exception: ProductExceptions.DuplicateProductException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.message)
    }
    @ExceptionHandler(ProductExceptions.ProductNotExistException::class)
    fun handleNotFound(exception: ProductExceptions.ProductNotExistException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }
    @ExceptionHandler(ProductExceptions.NullProductIdException::class)
    fun handleNullId(exception: ProductExceptions.NullProductIdException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): ResponseEntity<Map<String,String>>{
        val errors = mutableMapOf<String, String>()
        exception.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage ?: "Invalid value"
            errors[fieldName] = errorMessage
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }
}