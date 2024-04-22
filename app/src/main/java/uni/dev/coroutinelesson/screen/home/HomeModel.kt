package uni.dev.coroutinelesson.screen.home

import uni.dev.coroutinelesson.model.User
import uni.dev.coroutinelesson.networking.SignInUpService

class HomeModel(val signInUpService: SignInUpService) {
    suspend fun getUser (token:String): User? {
        return signInUpService.getUser(token)
    }
}