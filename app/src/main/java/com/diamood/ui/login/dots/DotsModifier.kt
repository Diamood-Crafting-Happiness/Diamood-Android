package com.diamood.ui.login.dots

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
    populationFactor: Float = 0.1f,
) = this.composed {
    val dotsModel = remember {
        DotsModel(
            DotsState(
                dotRadius = dotRadius,
                speed = speed
            )
        )
    }

    LaunchedEffect(speed, dotRadius, populationFactor) {
        dotsModel.populationControl(speed, dotRadius, populationFactor)
    }

    LaunchedEffect(Unit) {
        var lastFrame = 0L
        while (isActive) {
            val nextFrame = awaitFrame() / 100_000L
            if (lastFrame != 0L) {
                val period = nextFrame - lastFrame
                dotsModel.next(period)
            }
            lastFrame = nextFrame
        }
    }

    pointerInput(Unit) {
        detectDragGestures(
            onDragStart = { offset ->
                dotsModel.pointerDown(offset)
            },
            onDragEnd = {
                dotsModel.pointerUp()
            },
            onDragCancel = {
                dotsModel.pointerUp()
            },
            onDrag = { change, dragAmount ->
                dotsModel.pointerMove(dragAmount)
                change.consume()
            }
        )
    }
    .onSizeChanged {
        dotsModel.sizeChanged(it, populationFactor)
    }
    .drawBehind {
        val allDots =
            with(dotsModel.dotsAndLinesState) { (dots + pointer).filterNotNull() }

        allDots.forEach {
            drawCircle(contentColor, radius = dotRadius, center = it.position)
        }
    }
}

@Immutable
class DotsModel(
    initialDotsAndLinesState: DotsState
) {
    var dotsAndLinesState by mutableStateOf(initialDotsAndLinesState)

    fun populationControl(
        speed: Float,
        dotRadius: Float,
        populationFactor: Float
    ) {
        dotsAndLinesState = dotsAndLinesState.copy(
            speed = speed,
            dotRadius = dotRadius
        ).populationControl(populationFactor)
    }

    fun next(period: Long) {
        dotsAndLinesState = dotsAndLinesState.next(period)
    }

    fun sizeChanged(size: IntSize, populationFactor: Float) {
        dotsAndLinesState = dotsAndLinesState.sizeChanged(
            size = size,
            populationFactor = populationFactor
        )
    }

    fun pointerDown(offset: Offset) {
        dotsAndLinesState = dotsAndLinesState.copy(
            pointer = Dot(
                position = offset,
                vector = Offset.Zero
            )
        )
    }

    fun pointerMove(offset: Offset) {
        val currentPointer = dotsAndLinesState.pointer ?: return

        dotsAndLinesState = dotsAndLinesState.copy(
            pointer = dotsAndLinesState.pointer?.copy(
                position = currentPointer.position + offset,
                vector = Offset.Zero
            )
        )
    }

    fun pointerUp() {
        dotsAndLinesState = dotsAndLinesState.copy(pointer = null)
    }
}