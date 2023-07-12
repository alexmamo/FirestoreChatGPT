package ro.alexmamo.firestorechatgpt.presentation.request_name.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.job
import ro.alexmamo.firestorechatgpt.core.EMPTY_NAME_MESSAGE
import ro.alexmamo.firestorechatgpt.core.EMPTY_STRING
import ro.alexmamo.firestorechatgpt.core.NAME_PLACEHOLDER
import ro.alexmamo.firestorechatgpt.core.OPEN_CHAT
import ro.alexmamo.firestorechatgpt.core.Utils.Companion.showMessage

@Composable
@ExperimentalComposeUiApi
fun RequestNameContent(
    padding: PaddingValues,
    navigateToChatScreen: (name: String) -> Unit
) {
    var name by remember { mutableStateOf(EMPTY_STRING) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusRequester = FocusRequester()

        TextField(
            value = name,
            onValueChange = { newName ->
                name = newName
            },
            placeholder = {
                Text(
                    text = NAME_PLACEHOLDER
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.focusRequester(focusRequester)
        )
        LaunchedEffect(Unit) {
            coroutineContext.job.invokeOnCompletion {
                focusRequester.requestFocus()
            }
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Button(
            onClick = {
                if (name.isNotEmpty()) {
                    navigateToChatScreen(name)
                } else {
                    showMessage(context, EMPTY_NAME_MESSAGE)
                }
            }
        ) {
            Text(
                text = OPEN_CHAT,
                fontSize = 15.sp
            )
        }
    }
}