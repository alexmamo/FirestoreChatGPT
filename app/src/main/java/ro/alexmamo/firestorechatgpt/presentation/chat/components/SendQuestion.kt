package ro.alexmamo.firestorechatgpt.presentation.chat.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firestorechatgpt.components.ProgressBar
import ro.alexmamo.firestorechatgpt.core.Utils.Companion.print
import ro.alexmamo.firestorechatgpt.domain.model.Response.Failure
import ro.alexmamo.firestorechatgpt.domain.model.Response.Loading
import ro.alexmamo.firestorechatgpt.domain.model.Response.Success
import ro.alexmamo.firestorechatgpt.presentation.chat.ChatViewModel

@Composable
fun SendQuestion(
    viewModel: ChatViewModel = hiltViewModel()
) {
    when(val sendQuestionResponse = viewModel.sendQuestionResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> print(sendQuestionResponse.e)
    }
}