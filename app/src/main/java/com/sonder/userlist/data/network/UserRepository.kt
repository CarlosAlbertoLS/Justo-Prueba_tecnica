package com.sonder.userlist.data.network

import com.sonder.userlist.data.network.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: ApiService){
    suspend fun getRepository():Response<UserResponse>{
        return api.getUsers(20)
    }

    suspend fun getUserByGender(gender: String): Response<UserResponse>{
        return api.getUserByGender(gender)
    }
}