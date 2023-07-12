package ro.alexmamo.firestorechatgpt.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ro.alexmamo.firestorechatgpt.R

@Composable
fun ThreeDots(
    circleColor: Color = colorResource(R.color.purple_700),
    circleSize: Dp = 4.dp,
    animationDelay: Int = 400,
    initialAlpha: Float = 0.3f
) {
    val circles = listOf(
        remember {
            Animatable(
                initialValue = initialAlpha
            )
        },
        remember {
            Animatable(
                initialValue = initialAlpha
            )
        },
        remember {
            Animatable(
                initialValue = initialAlpha
            )
        }
    )

    circles.forEachIndexed { index, anim ->
        LaunchedEffect(Unit) {
            delay(
                timeMillis = (animationDelay / circles.size).toLong() * index
            )
            anim.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    Row(
        modifier = Modifier.padding(4.dp)
    ) {
        circles.forEachIndexed { index, anim ->
            if (index != 0) {
                Spacer(
                    modifier = Modifier.width(
                        width = 2.dp
                    )
                )
            }
            Box(
                modifier = Modifier.size(
                    size = circleSize
                ).clip(
                    shape = CircleShape
                ).background(
                    color = circleColor.copy(
                        alpha = anim.value
                    )
                )
            )
        }
    }
}