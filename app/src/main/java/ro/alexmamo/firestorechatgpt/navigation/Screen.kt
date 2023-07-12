package ro.alexmamo.firestorechatgpt.navigation

import ro.alexmamo.firestorechatgpt.core.CHAT_SCREEN_ROUTE
import ro.alexmamo.firestorechatgpt.core.REQUEST_NAME_SCREEN_ROUTE

sealed class Screen(val route: String) {
    object RequestNameScreen: Screen(REQUEST_NAME_SCREEN_ROUTE)
    object ChatScreen: Screen(CHAT_SCREEN_ROUTE)
}