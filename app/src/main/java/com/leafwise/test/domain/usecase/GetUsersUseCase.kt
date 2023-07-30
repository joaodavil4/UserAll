package com.leafwise.test.domain.usecase

import com.leafwise.test.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {
    operator fun invoke() : Flow<List<User>>
}