package uni.dev.coroutinelesson.screen.signIn

import android.util.Log
import uni.dev.coroutinelesson.model.SignIn
import uni.dev.coroutinelesson.model.User
import uni.dev.coroutinelesson.networking.SignInUpService

class SignInModel(private val signInUpService: SignInUpService) {
    suspend fun signIn(signIn: SignIn): User? {
        return try {
            signInUpService.login(signIn)
        }catch (e:Exception){
            Log.d("TAG", "signIn: ${e.cause}")
            null
        }
    }
}