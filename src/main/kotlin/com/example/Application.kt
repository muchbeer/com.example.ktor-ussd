package com.example

import com.example.db.DatabaseFactory
import io.ktor.server.application.*
import com.example.routes.*
import com.example.service.UssdServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    val portNumber = System.getenv("PORT")?.toInt() ?: 8083


    embeddedServer(Netty, port = 8080, host = "192.168.31.23" ) {
     val dbConnect =   DatabaseFactory.init()

        val serviceUSSD = UssdServiceImpl(dbConnect)

        install(ContentNegotiation) {
            jackson()
        }

        configureRouting(serviceUSSD)
    }.start(wait = true)
}


