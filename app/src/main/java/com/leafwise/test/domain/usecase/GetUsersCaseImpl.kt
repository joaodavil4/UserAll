package com.leafwise.test.domain.usecase

import androidx.annotation.WorkerThread
import com.leafwise.test.data.repository.UserRepository
import kotlinx.coroutines.flow.flow

class GetUsersUseCaseImp (private val userRepository: UserRepository) : GetUsersUseCase {

    @WorkerThread
    override fun invoke(
    ) = flow {
        userRepository.getUsers {
        }.collect {
            emit(it)
        }
    }
}
