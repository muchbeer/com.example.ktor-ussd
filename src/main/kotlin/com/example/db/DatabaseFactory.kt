package com.example.db

import org.ktorm.database.Database

object DatabaseFactory {
    private val mUsername = "cfzrcvztatm0hqv4"
    private val mPassword = "q1rvx0ypdzj42w52"

    fun init(): Database {
        val ktormDb: Database
        val jdbcUrl =
            "jdbc:mysql://n4m3x5ti89xl6czh.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/uxfaiijs8uwb06ud?autoReconnect=true&useSSL=false"

        ktormDb = Database.connect(
            url = jdbcUrl,
            driver = "com.mysql.cj.jdbc.Driver",
            user = mUsername,
            password = mPassword
        )
        return ktormDb
    }

}