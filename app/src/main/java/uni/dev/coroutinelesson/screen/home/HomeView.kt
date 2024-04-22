package uni.dev.coroutinelesson.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(vm: HomeViewModel) {
    val token = vm.getToken()
    val user = vm.user.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { }, actions = {
            IconButton(onClick = { vm.onLogOut() }) {
                Icon(Icons.Rounded.ExitToApp, contentDescription = "")
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .padding(
                    horizontal = 16.dp
                ), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start
        ) {

            if (user.value != null) Column {

                Text(text = "Username: ${user.value!!.username}")
                Text(text = "Email: ${user.value!!.email}")
                Text(text = "Firstname: ${user.value!!.firstName}")
                Text(text = "Lastname: ${user.value!!.lastName}")
                Text(text = "Token: ${user.value!!.token}")
            }

        }
    }
}