package com.example.db

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UssdTable : Table<UssdEntity>("ussd"){

    val sessionId = varchar("sessionId").primaryKey().bindTo { it.sessionId }
    val phoneNumber = varchar("phoneNumber").bindTo { it.phoneNumber }
    val networkCode = varchar("networkCode").bindTo { it.networkCode }
    val serviceCode = varchar("serviceCode").bindTo { it.serviceCode }
    val text = varchar("text").bindTo { it.text }
}

interface UssdEntity : Entity<UssdEntity> {

    companion object : Entity.Factory<UssdEntity>()
    val id : Int
    val sessionId : String
    val phoneNumber : String
    val networkCode : String
    val serviceCode : String
    val text : String
}