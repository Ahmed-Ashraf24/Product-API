package com.example.demo_api

import org.apache.coyote.BadRequestException

sealed interface ProductExceptions{
    class DuplicateProductException(override val message: String): RuntimeException(message),ProductExceptions
    class ProductNotExistException(override val message: String): NoSuchElementException(message),ProductExceptions
    class NullProductIdException(override val message: String): BadRequestException(message),ProductExceptions
}