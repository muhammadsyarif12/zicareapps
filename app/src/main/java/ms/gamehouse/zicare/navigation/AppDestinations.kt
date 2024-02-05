package ms.gamehouse.zicare.navigation

interface AppDestinations {
    val route: String
}

object Login : AppDestinations {
    override val route = "login"
}

object SignUp : AppDestinations {
    override val route = "signUp"
}

object Profile : AppDestinations {
    override val route = "profile"
}