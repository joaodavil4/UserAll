package com.leafwise.test.data.service

import com.leafwise.test.data.response.UserResponse
import com.leafwise.test.domain.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users/")
    suspend fun getUsers(): List<User>
}