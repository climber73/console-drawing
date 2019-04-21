package com.credit_suisse.drawing.console

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

import java.lang.IllegalArgumentException

class ConsoleCanvasTest {

    private lateinit var canvas: ConsoleCanvas

    @Test
    fun `test creation, happy path`() {
        canvas = ConsoleCanvas(1, 1, 10, 10)
        assertThat(canvas.width).isEqualTo(10)
        assertThat(canvas.height).isEqualTo(10)

        canvas = ConsoleCanvas(1, 1, 1, 1)
        assertThat(canvas.width).isEqualTo(1)
        assertThat(canvas.height).isEqualTo(1)

        canvas = ConsoleCanvas(-1, -1, 1, 1)
        assertThat(canvas.width).isEqualTo(3)
        assertThat(canvas.height).isEqualTo(3)
    }

    @Test
    fun `test creation, wrong dims`() {
        assertThatThrownBy { ConsoleCanvas(1, 1, -1, -1) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { ConsoleCanvas(1, 1, 1000, 1000) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun contain() {
        canvas = ConsoleCanvas(1, 1, 10, 10)
        assertThat(canvas.contain(ConsolePoint(5, 5))).isTrue()
        assertThat(canvas.contain(ConsolePoint(1, 1))).isTrue()
        assertThat(canvas.contain(ConsolePoint(10, 10))).isTrue()
        assertThat(canvas.contain(ConsolePoint(0, 0))).isFalse()
        assertThat(canvas.contain(ConsolePoint(11, 11))).isFalse()
        assertThat(canvas.contain(ConsolePoint(-1, 5))).isFalse()

        canvas = ConsoleCanvas(-10, -10, 0, 0)
        assertThat(canvas.contain(ConsolePoint(-5, -5))).isTrue()
        assertThat(canvas.contain(ConsolePoint(10, 10))).isFalse()
        assertThat(canvas.contain(ConsolePoint(0, 0))).isTrue()
        assertThat(canvas.contain(ConsolePoint(11, 11))).isFalse()
        assertThat(canvas.contain(ConsolePoint(-1, 5))).isFalse()
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