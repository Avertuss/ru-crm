package ru.sphera

import io.micronaut.runtime.Micronaut.*
import org.h2.tools.Server
fun main(args: Array<String>) {
	Server.createWebServer().start()
	run(*args)
}

