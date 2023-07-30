package com.leafwise.test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leafwise.test.domain.model.User
import com.leafwise.test.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _userUiState = MutableStateFlow<UiState>(UiState.Empty)
    val userUiState: StateFlow<UiState> = _userUiState.asStateFlow()

    fun loadUsers(){
        _userUiState.value = UiState.Loading
        viewModelScope.launch {
            getUsersUseCase.invoke().collect { users ->
                _userUiState.value = UiState.Success(users)
            }
        }
    }


}

sealed interface UiState {
    object Empty: UiState
    object Loading: UiState
    data class Success(val data: List<User>) : UiState
}