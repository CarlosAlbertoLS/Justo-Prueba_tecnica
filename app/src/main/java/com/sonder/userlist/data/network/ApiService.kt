package com.sonder.userlist.data.network

import com.sonder.userlist.data.network.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int
    ):Response<UserResponse>

    @GET("api/")
    suspend fun getUserByGender(
        @Query("gender") gender: String
    ):Response<UserResponse>
}