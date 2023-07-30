package com.leafwise.test.data.repository

import com.leafwise.test.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(
        onComplete: () -> Unit,
    ): Flow<List<User>>
}