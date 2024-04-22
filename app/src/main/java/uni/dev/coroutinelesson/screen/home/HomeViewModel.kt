package uni.dev.coroutinelesson.screen.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uni.dev.coroutinelesson.model.User
import uni.dev.coroutinelesson.navigation.Screen
import uni.dev.coroutinelesson.util.SharedHelper

class HomeViewModel(model: HomeModel, val navController: NavController, context: Context) :
    ViewModel() {
    private val userToken = SharedHelper.getInstance(context).getToken()

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        viewModelScope.launch {
            _user.value = model.getUser(userToken!!)
        }
    }

    fun getToken(): String {
        return userToken!!
    }

    fun onLogOut() {
        navController.navigate(Screen.SignIn.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }
}