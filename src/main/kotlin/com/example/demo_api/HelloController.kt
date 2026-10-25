package com.example.demo_api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class HelloResponse(
        val message:String,
        val success:Boolean,
        )

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): HelloResponse {
        return HelloResponse(
            message = "hello user ",
            success = true
        )
    }
}