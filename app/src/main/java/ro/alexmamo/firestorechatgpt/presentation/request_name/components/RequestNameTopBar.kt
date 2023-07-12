package ro.alexmamo.firestorechatgpt.presentation.request_name.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ro.alexmamo.firestorechatgpt.R

@Composable
fun RequestNameTopBar() {
    TopAppBar (
        title = {
            Text(
                text = stringResource(
                    id = R.string.app_name
                )
            )
        }
    )
}