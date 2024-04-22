package uni.dev.coroutinelesson.util

import android.content.Context
import android.content.SharedPreferences

class SharedHelper private constructor(context: Context) {
    private val shared = context.getSharedPreferences("app_share_prefs", 0)
    val edit: SharedPreferences.Editor = shared.edit()

    companion object {
        var instance: SharedHelper? = null

        fun getInstance(context: Context): SharedHelper {
            if (instance == null) instance = SharedHelper(context)
            return instance!!
        }
    }

    private val tokenKEY = "token"

    fun saveToken(token: String) {
        edit.putString(tokenKEY, token).apply()
    }

    fun getToken(): String? {
        return shared.getString(tokenKEY, null)
    }

    fun removeToken() {
        edit.remove(tokenKEY).apply()
    }
}