package com.example.service

import com.example.db.SchoolTable
import com.example.db.SchoolTable.region
import com.example.db.SchoolTable.school
import com.example.db.SchoolTable.sex
import com.example.db.UssdTable
import com.example.db.UssdTable.networkCode
import com.example.db.UssdTable.phoneNumber
import com.example.db.UssdTable.serviceCode
import com.example.db.UssdTable.sessionId
import com.example.db.UssdTable.text
import com.example.model.School
import com.example.model.UssdModel
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class UssdServiceImpl(val ktormDb : Database) : UssdService {
    override fun retrieveAllSchool(): List<School> {
        return ktormDb.sequenceOf(SchoolTable).toList().map {
            School(
                id = it.id,
                school = it.school,
                region = it.region,
                sex = it.sex
            )
        }
    }

    override fun findSchoolByName(name : String): School? {
        val schoolEntity = ktormDb.sequenceOf(SchoolTable).firstOrNull {
            it.school eq name
        }?.let { School(id = it.id, school = it.school, region = it.region, sex = it.sex) }
        return schoolEntity
    }

    override fun insertSchool(mSchool: School): School? {
        val schoolID : Int =   ktormDb.insertAndGenerateKey(SchoolTable) {
            set(school, mSchool.school)
            set(region, mSchool.region)
            set(sex, mSchool.sex)
        } as Int

        return School(id = schoolID, school = mSchool.school,
            region = mSchool.region, sex = mSchool.sex)
    }

    override fun retrieveAllUSSD(): List<UssdModel> {
     return ktormDb.sequenceOf(UssdTable).toList().map {
         UssdModel(
             sessionId = it.sessionId,
             phoneNumber = it.phoneNumber,
             networkCode = it.networkCode,
             serviceCode = it.serviceCode,
             text = it.text
         )
     }
    }

    override fun findUSSDBySessionId(inputSessionID: String): UssdModel? {
        return ktormDb.sequenceOf(UssdTable).firstOrNull() {
            it.sessionId eq sessionId
        }?.let { UssdModel(sessionId = it.sessionId, phoneNumber = it.phoneNumber,
            networkCode = it.networkCode, serviceCode = it.serviceCode, text = it.text) }
    }

    override fun insertUSSD(ussd: UssdModel): UssdModel? {
        val ussdID : Int = ktormDb.insert(UssdTable) {
            set(sessionId, ussd.sessionId)
            set(phoneNumber, ussd.phoneNumber)
            set(networkCode, ussd.networkCode)
            set(serviceCode, ussd.serviceCode)
            set(text, ussd.text)
        } as Int


        return if(ussdID >=0)  { UssdModel(sessionId = ussd.sessionId, phoneNumber = ussd.phoneNumber,
            networkCode = ussd.networkCode, serviceCode = ussd.serviceCode, text = ussd.text) } else null

    }

    override fun updateUSSDBySessionID(inputSessionID: String, ussd: UssdModel): Boolean {
      val updteType =  ktormDb.update(UssdTable) {
            set(sessionId, ussd.sessionId)
            set(phoneNumber, ussd.phoneNumber)
            set(serviceCode, ussd.serviceCode)
            set(networkCode, ussd.networkCode)
            set(text, ussd.text)

            where { it.sessionId eq inputSessionID }
        }
     return   updteType >= 0
    }
}