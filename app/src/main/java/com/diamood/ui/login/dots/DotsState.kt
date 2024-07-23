package com.diamood.ui.login.dots

import androidx.compose.ui.unit.IntSize
import com.diamood.ui.login.dots.Dot.Companion.next
import kotlin.math.roundToInt

data class DotsState(
    val dots: List<Dot> = emptyList(),
    val size: IntSize = IntSize.Zero,
    val speed: Float
) {

    companion object {
        fun DotsState.sizeChanged(
            size: IntSize,
            populationFactor: Float
        ) : DotsState {
            if (size == this.size) return this
            return copy(
                dots = (0..size.realPopulation(populationFactor)).map {
                    Dot.create(size)
                },
                size = size
            )
        }

        fun DotsState.next(): DotsState {
            return copy(
                dots = dots.map {
                    it.next()
                }
            )
        }

        fun DotsState.populationControl(populationFactor: Float): DotsState {
            val count = size.realPopulation(populationFactor = populationFactor)

            return if(count < dots.size) {
                copy(dots = dots.shuffled().take(count))
            } else {
                copy(dots = dots + (0..count-dots.size).map { Dot.create(size) })
            }
        }

        private fun IntSize.realPopulation(populationFactor: Float): Int {
            return (width * height / 10_000 * populationFactor).roundToInt()
        }
    }
}