package com.example.demo_api

sealed interface ProductExceptions{
    class DuplicateProductException(override val message: String): RuntimeException(message),ProductExceptions
    class ProductNotExistException(override val message: String): NoSuchElementException(message),ProductExceptions
}