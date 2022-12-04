package com.tolgabayrak.kotlinapiserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinApiServerApplication

fun main(args: Array<String>) {
	runApplication<KotlinApiServerApplication>(*args)
}
