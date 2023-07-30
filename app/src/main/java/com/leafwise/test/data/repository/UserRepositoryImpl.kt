package com.leafwise.test.data.repository

import androidx.annotation.WorkerThread
import com.leafwise.test.data.service.UserClient
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

class UserRepositoryImp constructor(
    private val userClient: UserClient,
) : UserRepository {

    @WorkerThread
    override fun getUsers(
        onComplete: () -> Unit,
    ) = flow {
        val response = userClient.fetchUserList()
        emit(response)
    }.onCompletion { onComplete() }
}