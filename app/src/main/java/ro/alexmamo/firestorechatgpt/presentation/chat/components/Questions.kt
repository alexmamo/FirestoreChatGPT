package ro.alexmamo.firestorechatgpt.presentation.chat.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firestorechatgpt.components.ProgressBar
import ro.alexmamo.firestorechatgpt.core.Utils.Companion.print
import ro.alexmamo.firestorechatgpt.domain.model.Response.Failure
import ro.alexmamo.firestorechatgpt.domain.model.Response.Loading
import ro.alexmamo.firestorechatgpt.domain.model.Response.Success
import ro.alexmamo.firestorechatgpt.domain.repository.Questions
import ro.alexmamo.firestorechatgpt.presentation.chat.ChatViewModel

@Composable
fun Questions(
    viewModel: ChatViewModel = hiltViewModel(),
    chatContent: @Composable (questions: Questions) -> Unit
) {
    when(val questionsResponse = viewModel.questionsResponse) {
        is Loading -> ProgressBar()
        is Success -> chatContent(questionsResponse.data)
        is Failure -> print(questionsResponse.e)
    }
}