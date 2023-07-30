package com.leafwise.test.data.service

import com.leafwise.test.domain.model.User

class UserClient constructor(
    private val userService: UserService
) {

    suspend fun fetchUserList(): List<User> =
        userService.getUsers()
}