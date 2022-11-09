package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

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

    @GetMapping("/csv", produces = [MediaType.TEXT_EVENT_STREAM_VALUE + ";charset=utf-8"])
    fun getCsv(): ResponseEntity<Flux<String>> {
        return ResponseEntity.ok(
            Flux.interval(Duration.ofSeconds(1))
                .map { """aaa, test, äöüß, 中文内码, ✅""" }
        )
    }

    @GetMapping("/csv-non-utf8", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getCsvNonUtf(): ResponseEntity<Flux<String>> {
        return ResponseEntity.ok(
            Flux.interval(Duration.ofSeconds(1))
                .map { """aaa, test, äöüß, 中文内码, ✅""" }
        )
    }
}