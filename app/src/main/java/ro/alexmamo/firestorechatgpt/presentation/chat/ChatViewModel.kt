package ro.alexmamo.firestorechatgpt.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.alexmamo.firestorechatgpt.domain.model.Response.Loading
import ro.alexmamo.firestorechatgpt.domain.model.Response.Success
import ro.alexmamo.firestorechatgpt.domain.repository.ChatRepository
import ro.alexmamo.firestorechatgpt.domain.repository.QuestionsResponse
import ro.alexmamo.firestorechatgpt.domain.repository.SendQuestionResponse
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repo: ChatRepository
): ViewModel() {
    var questionsResponse by mutableStateOf<QuestionsResponse>(Loading)
        private set
    var sendQuestionResponse by mutableStateOf<SendQuestionResponse>(Success(false))
        private set

    init {
        getQuestions()
    }

    private fun getQuestions() = viewModelScope.launch {
        repo.getQuestionsFromFirestore().collect { response ->
            questionsResponse = response
        }
    }

    fun sendQuestion(name: String, question: String) = viewModelScope.launch {
        sendQuestionResponse = Loading
        sendQuestionResponse = repo.sendQuestionToFirestore(name, question)
    }
}