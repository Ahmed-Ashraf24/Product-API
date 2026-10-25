package com.example.demo_api

import org.springframework.stereotype.Service



@Service
class ProductsService{
    private val products = mutableListOf(
        Product(
            id = 1,
            name = "Keyboard",
            price = 750.0
        ),
        Product(
            id = 2,
            name = "Wireless Mouse",
            price = 350.0
        ),
        Product(
            id = 3,
            name = "Gaming Monitor",
            price = 4500.0
        ),
        Product(
            id = 4,
            name = "USB-C Hub",
            price = 450.0
        ),
        Product(
            id = 5,
            name = "External SSD 1TB",
            price = 2200.0
        ),
        Product(
            id = 6,
            name = "Noise-Canceling Headphones",
            price = 1800.0
        ),
        Product(
            id = 7,
            name = "Laptop Stand",
            price = 290.0
        ),
        Product(
            id = 8,
            name = "Webcam 1080p",
            price = 600.0
        ),
        Product(
            id = 9,
            name = "Bluetooth Speaker",
            price = 950.0
        ),
        Product(
            id = 10,
            name = "Desk Mat",
            price = 150.0
        )
    )
    fun getAllProducts() = products.toList()
    fun updateProduct(newProduct: Product): Result<Product> {
        if (products.find { it.id == newProduct.id } == null) {
         return   Result.failure(
             ProductExceptions.ProductNotExistException("Product with id ${newProduct.id} does not exist.")
            )
        }
        products.apply {
            replaceAll{product->
                if(product.id==newProduct.id)
                    newProduct
                else
                    product
            }
        }
      return  Result.success(products.first { it.id == newProduct.id })
    }
    fun addProduct(newProduct: Product): Result<Product> {
        if (products.find { it.id == newProduct.id } != null) {
            return Result.failure(ProductExceptions.DuplicateProductException("Product with id ${newProduct.id} already exists"))
        }
        products.apply {
            add(newProduct)
        }
        return Result.success(products.first { it.id == newProduct.id })
    }
    fun deleteProduct(id: Int): Result<String> {
        if (products.find { it.id == id} == null) {
           return Result.failure(ProductExceptions.ProductNotExistException("Product with id $id not found"))
        }
        products.apply {
            removeIf { it.id == id }
        }
        return Result.success("Item with id $id was removed successfully")
    }
}