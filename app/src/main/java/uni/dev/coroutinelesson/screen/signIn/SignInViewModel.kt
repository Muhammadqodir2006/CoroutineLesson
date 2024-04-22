package uni.dev.coroutinelesson.screen.signIn

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import uni.dev.coroutinelesson.model.SignIn
import uni.dev.coroutinelesson.navigation.Screen
import uni.dev.coroutinelesson.util.SharedHelper

class SignInViewModel(
    private val model: SignInModel, private val navController: NavController, context: Context
) : ViewModel() {
    private val _username = MediatorLiveData("")
    val username: LiveData<String> = _username

    private val _password = MediatorLiveData("")
    val password: LiveData<String> = _password

    fun updateUsername(it: String) {
        _username.value = it
    }

    fun updatePassword(it: String) {
        _password.value = it
    }

    init {
        val token = SharedHelper.getInstance(context).getToken()
        if (token != null) navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun onLoginClick(context: Context) {
        val signIn = SignIn(username = _username.value!!, password = _password.value!!)
        viewModelScope.launch {
            val user = model.signIn(signIn)
            if (user != null) {
                SharedHelper.getInstance(context).saveToken(user.token)
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
                Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
            } else {
                _password.value = ""
                Toast.makeText(context, "Username or password incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }


}