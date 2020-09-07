package com.example.lewjun

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class App {
    @GetMapping("/hello")
    fun hello(@RequestParam(name = "name") name: String = "World") = String.format("Hello %s!", name)

    @GetMapping("/")
    fun index() = "/"
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
