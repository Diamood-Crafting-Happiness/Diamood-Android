package com.diamood.ui.login.dots

import androidx.annotation.FloatRange
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import java.util.Random

data class Dot(
    val position: Offset,
    val vector: Offset,
    @FloatRange(0.0, 1.0) val alpha: Float
) {
    companion object {

        /**
         * Calculate where this dot will be in the next iteration.
         *
         * @param borders Size of the canvas where dots bounce.
         * @param durationMillis How long time is going to pass until next iteration.
         * @param dotRadius The radius of this dot when it is drawn.
         * @param speedCoefficient Although there is vector that indicates motion, this
         * parameter is used to speed up or down the animation at will.
         */
        fun Dot.next(
            alphaRate: Float = 0.005f
        ): Dot {
            return Dot(
                position = position,
                vector = vector,
                alpha = alpha
            ).let { (position, vector) ->
                Dot(
                    position = position,
                    vector = vector,
                    alpha = alpha - alphaRate
                )
            }
        }

        /**
         * Create a random dot instance belonging to @param borders.
         */
        fun create(borders: IntSize): Dot {
            return Dot(
                position = Offset(
                    (0..borders.width).random().toFloat(),
                    (0..borders.height).random().toFloat()
                ),
                vector = Offset(
                    // First, randomize direction. Second, randomize amplitude of speed vector.
                    listOf(
                        -1f,
                        1f
                    ).random() * ((borders.width.toFloat() / 100f).toInt()..(borders.width.toFloat() / 10f).toInt()).random()
                        .toFloat(),
                    listOf(
                        -1f,
                        1f
                    ).random() * ((borders.height.toFloat() / 100f).toInt()..(borders.height.toFloat() / 10f).toInt()).random()
                        .toFloat()
                ),
                alpha = Random().nextFloat()
            )
        }
    }
}