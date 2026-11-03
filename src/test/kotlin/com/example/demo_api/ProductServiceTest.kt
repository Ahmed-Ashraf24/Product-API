package com.example.demo_api

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.Optional
import kotlin.test.BeforeTest

class ProductsServiceTest{
    lateinit var productRepository: ProductRepository
    lateinit var productService: ProductsService

    @BeforeTest
    fun repositorySetup() {
        productRepository = mockk<ProductRepository>()
        productService=ProductsService(productRepository)
    }
    @Test
fun `getAllProducts returns mapped products from repository`(){

        val product1= Product(
            id = 1,
            name = "keyboard",
            price = 300.0
        )
        val product2= Product(
            id = 2,
            name = "mouse",
            price = 250.0
        )
        val entity1=ProductEntity(
            id = 1,
            name = "keyboard",
            price = 300.0
        )
        val entity2= ProductEntity(
            id = 2,
            name = "mouse",
            price = 250.0
        )
        every { productRepository.findAll() } returns listOf(entity1, entity2)
       val result= productService.getAllProducts()
        assertEquals(listOf(product1, product2), result)
        verify(exactly = 1) { productRepository.findAll() }
}
    @Test
    fun `getAllProducts returns empty list`(){
        val productsService=ProductsService(productRepository)
        every { productRepository.findAll() } returns emptyList()
        productRepository.findAll()
        verify(exactly = 1) { productRepository.findAll() }
        assertEquals(emptyList<Product>(), productsService.getAllProducts())

    }
    @Test
    fun `add product test should success when products name doesn't exist in database`(){

        val product= Product(
            id = 3,
            name = "headset",
            price = 250.0
        )

        val entity= ProductEntity(
            id = 3,
            name = "headset",
            price = 250.0
        )
        every { productRepository.existsByName(product.name) } returns false
        every { productRepository.save(entity) } returns entity
        val result=productService.addProduct(product)
        assert(result.isSuccess)
        verify(exactly = 1) { productRepository.save(any()) }
    }
    @Test
    fun `add product test should fail if the product you adding is already exist in database`(){
        val product= Product(
            id = 3,
            name = "headset",
            price = 250.0
        )

        every { productRepository.existsByName(product.name) } returns true
        val result = productService.addProduct(product)
        assert(result.isFailure)
        verify(exactly = 0) { productRepository.save(any()) }
    }
    @Test
    fun `update product test should success when products id already exist in database `(){
        val product= Product(
            id = 3,
            name = "headset",
            price = 250.0
        )
        val entity= ProductEntity(
            id = 3,
            name = "headset",
            price = 250.0
        )
        every { productRepository.findById(product.id!!) } returns Optional.of(entity)
        every { productRepository.existsByName(product.name) } returns false
        every { productRepository.findByName(product.name) } returns entity
        every { productRepository.save(entity) } returns entity
        val result = productService.updateProduct(product)
        assertEquals(product, result.getOrNull())
    }
    @Test
    fun `update product test should fail if the product doesn't exist in database`(){
        val product= Product(
            id = 3,
            name = "headset",
            price = 250.0
        )
        every { productRepository.findById(product.id!!) } returns Optional.empty()
        val result = productService.updateProduct(product)
        assert(result.isFailure)
        verify(exactly = 0) { productRepository.save(any()) }
    }
    @Test
    fun `update product test should fail if product updated name already exists in the database with different id`(){
        val product= Product(
            id = 3,
            name = "headset",
            price = 250.0
        )
        val entity1= ProductEntity(
            id = 3,
            name = "keyboard",
            price = 500.0
        )
        val entity2= ProductEntity(
            id = 4,
            name = "headset",
            price = 250.0
        )
        every { productRepository.findById(product.id!!) } returns Optional.of(entity1)
        every { productRepository.findByName(product.name) } returns entity2
        val result = productService.updateProduct(product)
        assert(result.isFailure)
        verify(exactly = 0) { productRepository.save(any()) }
    }
    @Test
    fun `delete product test should success when products id exists in database`(){
        val productId=3
        val entity= ProductEntity(
            id = 3,
            name = "headset",
            price = 250.0
        )
        every { productRepository.findById(productId) } returns Optional.of(entity)
        every { productRepository.deleteById(entity.id!!) } returns Unit
        val result = productService.deleteProduct(productId)
        assertEquals("Item with id 3 was removed successfully", result.getOrNull())
        verify(exactly = 1) { productRepository.deleteById(productId) }
    }
    @Test
    fun `delete product test should fail if the product doesn't exist in database`(){
        val productId=4
        val entity= ProductEntity(
            id = 3,
            name = "headset",
            price = 250.0
        )
        every { productRepository.findById(productId) } returns Optional.empty()

        val result = productService.deleteProduct(productId)
        assert(result.isFailure)
        verify(exactly = 0) { productRepository.deleteById(productId) }
    }


}