package uni.dev.coroutinelesson.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uni.dev.coroutinelesson.networking.ApiClient
import uni.dev.coroutinelesson.networking.SignInUpService
import uni.dev.coroutinelesson.screen.home.HomeModel
import uni.dev.coroutinelesson.screen.home.HomeView
import uni.dev.coroutinelesson.screen.home.HomeViewModel
import uni.dev.coroutinelesson.screen.signIn.SignInModel
import uni.dev.coroutinelesson.screen.signIn.SignInView
import uni.dev.coroutinelesson.screen.signIn.SignInViewModel
import uni.dev.coroutinelesson.screen.signUp.SignUpModel
import uni.dev.coroutinelesson.screen.signUp.SignUpView
import uni.dev.coroutinelesson.screen.signUp.SignUpViewModel

@Composable
fun MainNavigation(navController: NavHostController) {

    val signInUpService = ApiClient.getInstance().create(SignInUpService::class.java)
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(Screen.SignIn.route) {
            val signInModel = SignInModel(signInUpService)
            val signInViewModel = SignInViewModel(signInModel, navController, context)
            SignInView(vm = signInViewModel)
        }
        composable(Screen.SignUp.route) {
            val signUpModel = SignUpModel(signInUpService)
            val signUpViewModel = SignUpViewModel(signUpModel)
            SignUpView(vm = signUpViewModel)
        }
        composable(Screen.Home.route) {
            val homeModel = HomeModel(signInUpService)
            val homeViewModel = HomeViewModel(homeModel, navController, context)
            HomeView(homeViewModel)
        }
    }
}