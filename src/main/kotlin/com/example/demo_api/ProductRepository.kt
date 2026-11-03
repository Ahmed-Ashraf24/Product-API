package com.example.demo_api

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<ProductEntity, Int> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): ProductEntity?

}