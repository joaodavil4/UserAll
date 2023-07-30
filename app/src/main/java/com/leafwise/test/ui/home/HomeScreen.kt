package com.leafwise.test.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leafwise.test.data.repository.UserRepository
import com.leafwise.test.data.repository.UserRepositoryImp
import com.leafwise.test.data.service.UserClient
import com.leafwise.test.data.service.UserService
import com.leafwise.test.data.network.RetrofitClient
import com.leafwise.test.domain.model.User
import com.leafwise.test.domain.usecase.GetUsersUseCase
import com.leafwise.test.domain.usecase.GetUsersUseCaseImp
import com.leafwise.test.ui.compositions.CardItem
import com.leafwise.test.ui.compositions.LoadingIndicator

//Should be injected
private val userService: UserService by lazy {
    val retrofit = RetrofitClient().provideRetrofit()
    retrofit.create(UserService::class.java)
}

private val userRepository: UserRepository by lazy {
    val userClient = UserClient(userService)
    UserRepositoryImp(userClient)
}

private val getUsersUseCase: GetUsersUseCase by lazy {
    GetUsersUseCaseImp(userRepository)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val homeViewModel = HomeViewModel(getUsersUseCase)
    val homeScreenUiState by remember { homeViewModel.userUiState }.collectAsState()
    homeViewModel.loadUsers()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeHeader()
            when(homeScreenUiState){
                is UiState.Loading -> {
                    LoadingIndicator()
                }

                is UiState.Success -> {
                    HomeContent((homeScreenUiState as UiState.Success).data)
                }

                else -> {}
            }
        }
    }
}

@Composable
fun HomeHeader(){
    Text(
        text = "Users",
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun HomeContent(users: List<User>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
        items(users){ user ->
            CardItem(
                image = user.pictureURL,
                title = "${user.firstName} ${user.lastName}",
                subtitle = user.email,
                description = user.phone
            )
        }
    })

}