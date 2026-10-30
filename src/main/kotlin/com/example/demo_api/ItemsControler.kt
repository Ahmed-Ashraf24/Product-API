package com.example.demo_api


import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

data class Product(val id: Int?=null, val name: String, val price: Double)
fun Product.toProductEntity(): ProductEntity = ProductEntity(id, name, price)
@RestController
class ItemsController(private val productsService: ProductsService) {
    @GetMapping("/product")
    fun getProducts() : List<Product>{
        return productsService.getAllProducts()
    }
    @PutMapping("/product")
    fun updateProduct ( @RequestBody newProduct: Product):Product {
      productsService.updateProduct(newProduct).fold(
          onSuccess = {product->
             return product
          },
          onFailure = {exception->
              throw exception
          }
      )

    }
    @PostMapping("/product")
    fun addProduct(@RequestBody newProduct: Product):Product {
        productsService.addProduct(newProduct.copy(id = null)).fold(
            onSuccess = {product->
                return product
            }
            , onFailure = {exception->
                throw exception
            }
        )
    }
    @DeleteMapping("/product/{id}")
    fun removeProduct(@PathVariable id: Int):String {
        productsService.deleteProduct(id).fold(
            onSuccess = {message->
                return message
            }
            , onFailure = {exception->
                throw exception
            }
        )
    }
}