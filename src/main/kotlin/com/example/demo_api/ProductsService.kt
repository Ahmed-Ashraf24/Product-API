package com.example.demo_api

import org.springframework.stereotype.Service


@Service
class ProductsService(val productRepository: ProductRepository) {
    fun getAllProducts() = productRepository.findAll().map { it.toProduct() }.toList()
    fun updateProduct(newProduct: Product): Result<Product> {

        if (newProduct.id == null) {
            return Result.failure(ProductExceptions.NullProductIdException("can't update a product with null id"))

        }
        val dbProduct = newProduct.id.let { productRepository.findById(it) }

        if (dbProduct.isEmpty) {
            return Result.failure(
                ProductExceptions.ProductNotExistException("Product with id ${newProduct.id} does not exist.")
            )
        }
        val savedProduct = productRepository.save(newProduct.toProductEntity())
        return Result.success(savedProduct.toProduct())
    }

    fun addProduct(newProduct: Product): Result<Product> {

        if (productRepository.existsByName(newProduct.name)) {
            return Result.failure(ProductExceptions.DuplicateProductException("Product with name ${newProduct.name} already exists"))
        }
        val savedProduct = productRepository.save(newProduct.toProductEntity())
        return Result.success(savedProduct.toProduct())
    }

    fun deleteProduct(id: Int): Result<String> {
        val dbProduct = productRepository.findById(id)
        if (dbProduct.isEmpty) {
            return Result.failure(ProductExceptions.ProductNotExistException("Product with id $id not found"))
        }
        productRepository.deleteById(id)
        return Result.success("Item with id $id was removed successfully")
    }
}