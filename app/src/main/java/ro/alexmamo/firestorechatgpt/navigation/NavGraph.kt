package ro.alexmamo.firestorechatgpt.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ro.alexmamo.firestorechatgpt.core.EMPTY_STRING
import ro.alexmamo.firestorechatgpt.core.NAME
import ro.alexmamo.firestorechatgpt.navigation.Screen.ChatScreen
import ro.alexmamo.firestorechatgpt.navigation.Screen.RequestNameScreen
import ro.alexmamo.firestorechatgpt.presentation.chat.ChatScreen
import ro.alexmamo.firestorechatgpt.presentation.request_name.RequestNameScreen

@Composable
@ExperimentalComposeUiApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = RequestNameScreen.route
    ) {
        composable(
            route = RequestNameScreen.route
        ) {
            RequestNameScreen(
                navigateToChatScreen = { name ->
                    navController.navigate("${ChatScreen.route}/${name}")
                }
            )
        }

        composable(
            route = "${ChatScreen.route}/{$NAME}",
            arguments = mutableStateListOf(
                navArgument(NAME) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString(NAME) ?: EMPTY_STRING
            ChatScreen(
                name = name,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}