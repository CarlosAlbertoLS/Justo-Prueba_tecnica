package com.sonder.userlist.domain

import com.sonder.userlist.data.network.UserRepository
import com.sonder.userlist.data.network.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class GetUserByGender @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(gender: String): Response<UserResponse>{
        return userRepository.getUserByGender(gender)
    }
}