package ms.gamehouse.zicare.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ms.gamehouse.zicare.view.login.LoginRoute
import ms.gamehouse.zicare.view.signup.SignupRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController,
        startDestination = Login.route,
        modifier = modifier){
        composable(Login.route){
            LoginRoute(
                onSignUpButtonClicked = {navController.navigate(SignUp.route)},
                navigateToProfileScreeen = {navController.navigate(Profile.route)}
                )
        }

        composable(SignUp.route){
            SignupRoute(
                navController = navController,
                navigateToLogin = {
                    navController.navigate(Login.route)
                }
            )
        }
    }
}