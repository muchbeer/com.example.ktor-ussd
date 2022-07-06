package com.example.model

data class UssdModel(
    val sessionId : String,
    val phoneNumber : String,
    val networkCode : String,
    val serviceCode : String,
    val text : String
)
