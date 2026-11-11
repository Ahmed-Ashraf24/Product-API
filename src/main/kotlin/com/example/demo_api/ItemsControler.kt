package com.example.demo_api


import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

data class Product(
    val id: Int? = null,
    @field:NotBlank(message = "the name must not be blank") val name: String,
    @field:PositiveOrZero("the price must be >= 0") val price: Double,
    val imageUrl:String?=null
)

fun Product.toProductEntity(): ProductEntity = ProductEntity(id, name, price,imageUrl)

@RestController
class ItemsController(private val productsService: ProductsService) {
    @GetMapping("/product")
    fun getProducts(): List<Product> {
        return productsService.getAllProducts()
    }

    @PutMapping("/product")
    fun updateProduct(@Valid @RequestBody newProduct: Product): Product {
        productsService.updateProduct(newProduct).fold(
            onSuccess = { product ->
                return product
            },
            onFailure = { exception ->
                throw exception
            }
        )

    }

    @PostMapping("/product")
    fun addProduct(@Valid @RequestBody newProduct: Product) :ResponseEntity<Product>{
        productsService.addProduct(newProduct.copy(id = null)).fold(
            onSuccess = { product ->
              return  ResponseEntity.status(HttpStatus.CREATED).body(product)

            }, onFailure = { exception ->
                throw exception
            }
        )
    }

    @DeleteMapping("/product/{id}")
    fun removeProduct(@PathVariable id: Int): String {
        productsService.deleteProduct(id).fold(
            onSuccess = { message ->
                return message
            }, onFailure = { exception ->
                throw exception
            }
        )
    }
}