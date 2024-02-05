package ms.gamehouse.zicare.view.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ms.gamehouse.zicare.data.model.User
import ms.gamehouse.zicare.ui.theme.ZiCareTheme
import ms.gamehouse.zicare.view.login.LoginScreen
import java.util.UUID

@AndroidEntryPoint
class ProfileActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newUser = User(
            "Muhammad Syarif",
            "msgamehouse11",
            "msgamehouse11@yopmail.com",
            "********",
            true)

        setContent {
            ZiCareTheme {
                // A surface container using the 'background' color from the theme
                ProfileScreen(newUser)
            }
        }
    }
}