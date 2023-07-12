package ro.alexmamo.firestorechatgpt.presentation.request_name

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import ro.alexmamo.firestorechatgpt.presentation.request_name.components.RequestNameContent
import ro.alexmamo.firestorechatgpt.presentation.request_name.components.RequestNameTopBar

@Composable
@ExperimentalComposeUiApi
fun RequestNameScreen(
    navigateToChatScreen: (name: String) -> Unit
) {
    Scaffold(
        topBar = {
            RequestNameTopBar()
        },
        content = { padding ->
            RequestNameContent(
                padding = padding,
                navigateToChatScreen = navigateToChatScreen
            )
        }
    )
}