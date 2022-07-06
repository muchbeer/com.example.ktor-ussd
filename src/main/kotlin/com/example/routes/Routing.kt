package com.example.routes

import com.example.model.School
import com.example.model.UssdModel
import com.example.service.UssdService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureRouting(repository : UssdService) {

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("USSD Project")
        }
    }
    routing {
        get("/schools") {
            call.respond(repository.retrieveAllSchool())
        }

        post("/register") {
            val addSchool = call.receive<School>()
            val response = repository.insertSchool(addSchool)
            call.respond(response ?: "Could not insert record")
        }

        post("/ussd") {


            val addUssd = call.receive<UssdModel>()

            if (repository.findUSSDBySessionId(addUssd.sessionId) != null) {
                repository.updateUSSDBySessionID(addUssd.sessionId, addUssd)
                call.respond(message = "Success ")
            } else {
                val response = repository.insertUSSD(addUssd)
                response?.let {
                } ?: call.respond(status = HttpStatusCode.BadRequest, message="Could not add try again")
            }

        }

    }
}
