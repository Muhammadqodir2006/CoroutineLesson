package uni.dev.coroutinelesson.networking

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uni.dev.coroutinelesson.model.SignIn
import uni.dev.coroutinelesson.model.User

interface SignInUpService {
    @POST("auth/login")
    suspend fun login(@Body signIn: SignIn): User?

    @GET("/auth/me")
    suspend fun getUser(@Query("token") token:String):User?
}