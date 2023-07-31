package com.sonder.userlist.domain

import com.sonder.userlist.data.network.UserRepository
import com.sonder.userlist.data.network.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Response<UserResponse> {
        return userRepository.getRepository()
    }
}