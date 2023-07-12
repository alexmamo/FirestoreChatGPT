package ro.alexmamo.firestorechatgpt.presentation.chat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.job
import ro.alexmamo.firestorechatgpt.R
import ro.alexmamo.firestorechatgpt.components.ThreeDots
import ro.alexmamo.firestorechatgpt.core.CHAT_GPT
import ro.alexmamo.firestorechatgpt.core.EMPTY_QUESTION_MESSAGE
import ro.alexmamo.firestorechatgpt.core.EMPTY_STRING
import ro.alexmamo.firestorechatgpt.core.NO_QUESTIONS_MESSAGE
import ro.alexmamo.firestorechatgpt.core.SEND
import ro.alexmamo.firestorechatgpt.core.QUESTION_PLACEHOLDER
import ro.alexmamo.firestorechatgpt.core.Utils.Companion.showMessage
import ro.alexmamo.firestorechatgpt.domain.repository.Questions

@Composable
fun ChatContent(
    padding: PaddingValues,
    questions: Questions,
    sendQuestion: (question: String) -> Unit
) {
    var question by remember { mutableStateOf(EMPTY_STRING) }
    val context = LocalContext.current
    val focusRequester = FocusRequester()

    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        if (questions.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1f).padding(8.dp)
            ) {
                items(
                    items = questions
                ) { question ->
                    question.apply {
                        prompt?.let {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = "${question.addedBy}: ${question.prompt}",
                                fontSize = 15.sp
                            )
                        }
                        response?.let {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = "$CHAT_GPT: ${question.response}",
                                color = colorResource(R.color.teal_700)
                            )
                        } ?: kotlin.run {
                            ThreeDots()
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f).padding(8.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = NO_QUESTIONS_MESSAGE,
                    fontSize = 15.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = question,
                onValueChange = { newQuestion ->
                    question = newQuestion
                },
                placeholder = {
                    Text(
                        text = QUESTION_PLACEHOLDER
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier.weight(1f).padding(
                    end = 4.dp
                ).focusRequester(focusRequester)
            )
            Button(
                modifier = Modifier.padding(
                    start = 4.dp
                ),
                onClick = {
                    if (question.isNotEmpty()) {
                        sendQuestion(question)
                        question = EMPTY_STRING
                    } else {
                        showMessage(context, EMPTY_QUESTION_MESSAGE)
                    }
                }
            ) {
                Text(
                    text = SEND,
                    fontSize = 15.sp
                )
            }
        }

        LaunchedEffect(Unit) {
            coroutineContext.job.invokeOnCompletion {
                focusRequester.requestFocus()
            }
        }
    }
}