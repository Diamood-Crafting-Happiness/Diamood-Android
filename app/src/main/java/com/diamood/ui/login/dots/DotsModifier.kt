package com.diamood.ui.login.dots

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.diamood.theme.BackgroundLight
import com.diamood.ui.login.dots.DotsState.Companion.next
import com.diamood.ui.login.dots.DotsState.Companion.populationControl
import com.diamood.ui.login.dots.DotsState.Companion.sizeChanged
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.isActive

fun Modifier.dots(
    contentColor: Color = BackgroundLight,
    dotRadius: Float = 4f,
    speed: Float = 0.05f,
    populationFactor: Float = 0.05f,
) = this.composed {
    val dotsModel = remember {
        DotsModel(
            DotsState(
                speed = speed
            )
        )
    }

    LaunchedEffect(speed, dotRadius, populationFactor) {
        dotsModel.populationControl(speed, dotRadius)
    }

    LaunchedEffect(Unit) {
        var lastFrame = 0L
        while (isActive) {
            val nextFrame = awaitFrame() / 100_000L

            if (awaitFrame() % 2 <= 0) dotsModel.populationControl(speed, populationFactor)

            if (lastFrame != 0L) {
                dotsModel.next()
            }
            lastFrame = nextFrame
        }
    }

    onSizeChanged {
        dotsModel.sizeChanged(it, populationFactor)
    }
        .drawBehind {
            val allDots = with(dotsModel.dotsState) { dots }

            allDots.forEach {
                drawCircle(contentColor, radius = dotRadius, center = it.position, alpha = it.alpha)
            }
        }
}

@Immutable
class DotsModel(
    initialDotsAndLinesState: DotsState
) {
    var dotsState by mutableStateOf(initialDotsAndLinesState)

    fun populationControl(
        speed: Float,
        populationFactor: Float
    ) {
        dotsState = dotsState.copy(
            speed = speed
        ).populationControl(populationFactor)
    }

    fun next() {
        dotsState = dotsState.next()
    }

    fun sizeChanged(size: IntSize, populationFactor: Float) {
        dotsState = dotsState.sizeChanged(
            size = size,
            populationFactor = populationFactor
        )
    }
}
