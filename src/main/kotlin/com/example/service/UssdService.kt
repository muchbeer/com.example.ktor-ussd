package com.example.service

import com.example.model.School
import com.example.model.UssdModel

interface UssdService {

   fun retrieveAllSchool() : List<School>

   fun findSchoolByName(name : String) : School?

   fun insertSchool(school: School) : School?

    fun retrieveAllUSSD() : List<UssdModel>

    fun findUSSDBySessionId(inputSessionID: String) : UssdModel?

    fun insertUSSD(ussd: UssdModel) : UssdModel?

    fun updateUSSDBySessionID(inputSessionID : String , ussd: UssdModel) : Boolean
}