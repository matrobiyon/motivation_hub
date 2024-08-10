package tj.motivation.hub.home.presantation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tj.motivation.hub.home.presantation.ui.screen.HomeScreen
import tj.motivation.hub.home.presantation.ui.screen.ProfileScreen

@Composable
fun HomeNavigation(navController : NavHostController)  {

    NavHost(navController = navController, startDestination = NavigationTags.HOME){
        composable(NavigationTags.HOME){
            HomeScreen(navController)
        }
        
        composable(NavigationTags.PROFILE){
            ProfileScreen(navController = navController)
        }
    }

}