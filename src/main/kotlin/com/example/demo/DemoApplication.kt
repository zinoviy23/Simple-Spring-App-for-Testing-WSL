package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@RestController
class MyController {
    @GetMapping("hello/{name}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable name: String): String {
        return """
            {
              "message": "Hello, $name!"
            }
        """.trimIndent()
    }
}