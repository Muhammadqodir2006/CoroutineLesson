package uni.dev.coroutinelesson.navigation

sealed class Screen(val route: String) {
    data object SignIn : Screen("signIn")
    data object SignUp : Screen("signUp")
    data object Home : Screen("main")
}