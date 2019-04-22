package com.creditsuisse.drawing.console

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

import java.lang.IllegalArgumentException

class ConsoleCanvasTest {

    private lateinit var canvas: ConsoleCanvas

    @Test
    fun `test creation, happy path`() {
        canvas = ConsoleCanvas(10, 10)
        assertThat(canvas.width).isEqualTo(10)
        assertThat(canvas.height).isEqualTo(10)

        canvas = ConsoleCanvas(1, 1)
        assertThat(canvas.width).isEqualTo(1)
        assertThat(canvas.height).isEqualTo(1)
    }

    @Test
    fun `test creation, wrong dims`() {
        assertThatThrownBy { ConsoleCanvas(-1, -1) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { ConsoleCanvas(0, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { ConsoleCanvas(81, 81) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `contain point`() {
        canvas = ConsoleCanvas(10, 10)
        assertThat(canvas.contains(ConsolePoint(1, 1))).isTrue()
        assertThat(canvas.contains(ConsolePoint(1, 10))).isTrue()
        assertThat(canvas.contains(ConsolePoint(10, 10))).isTrue()
        assertThat(canvas.contains(ConsolePoint(10, 1))).isTrue()
        assertThat(canvas.contains(ConsolePoint(0, 0))).isFalse()
        assertThat(canvas.contains(ConsolePoint(1, 0))).isFalse()
        assertThat(canvas.contains(ConsolePoint(0, 1))).isFalse()
        assertThat(canvas.contains(ConsolePoint(11, 11))).isFalse()
        assertThat(canvas.contains(ConsolePoint(11, 10))).isFalse()
        assertThat(canvas.contains(ConsolePoint(10, 11))).isFalse()
        assertThat(canvas.contains(ConsolePoint(-1, 1))).isFalse()
    }

    @Test
    fun `contain line`() {
        canvas = ConsoleCanvas(10, 10)
        assertThat(canvas.contains(ConsoleLine(1, 1, 1, 10))).isTrue()
        assertThat(canvas.contains(ConsoleLine(1, 10, 10, 10))).isTrue()
        assertThat(canvas.contains(ConsoleLine(10, 10, 10, 1))).isTrue()
        assertThat(canvas.contains(ConsoleLine(10, 1, 1, 1))).isTrue()

        assertThat(canvas.contains(ConsoleLine(0, 0, 0, 10))).isFalse()
        assertThat(canvas.contains(ConsoleLine(0, 10, 10, 10))).isFalse()
        assertThat(canvas.contains(ConsoleLine(10, 10, 10, 0))).isFalse()
        assertThat(canvas.contains(ConsoleLine(10, 0, 0, 0))).isFalse()

        assertThat(canvas.contains(ConsoleLine(-1, -1, -1, -10))).isFalse()
        assertThat(canvas.contains(ConsoleLine(-1, -10, -10, -10))).isFalse()
        assertThat(canvas.contains(ConsoleLine(-10, -10, -10, -1))).isFalse()
        assertThat(canvas.contains(ConsoleLine(-10, -1, -1, -1))).isFalse()
    }

    @Test
    fun `contain rect`() {
        canvas = ConsoleCanvas(10, 10)
        assertThat(canvas.contains(ConsoleRect(1, 1, 10, 10))).isTrue()
        assertThat(canvas.contains(ConsoleRect(10, 10, 1, 1))).isTrue()

        assertThat(canvas.contains(ConsoleRect(0, 0, 10, 10))).isFalse()
        assertThat(canvas.contains(ConsoleRect(10, 10, 0, 0))).isFalse()

        assertThat(canvas.contains(ConsoleRect(-1, -1, -10, -10))).isFalse()
        assertThat(canvas.contains(ConsoleRect(-10, -10, -1, -1))).isFalse()
    }

    @Test
    operator fun iterator() {
    }

    @Test
    fun add() {
    }

    @Test
    fun bucketFill() {
    }
}