package com.example.demo_api

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null,
    @Column(name = "pname",nullable = false)
    var name: String = "unknown",
    @Column(name = "pprice",nullable = false)
    val price: Double= 0.0 ,
    @Column(name = "image_url",nullable = true)
    val imageUrl:String?=null
)
fun ProductEntity.toProduct(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        imageUrl = this.imageUrl
    )
}