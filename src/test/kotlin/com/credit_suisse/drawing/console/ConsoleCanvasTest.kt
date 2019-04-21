package com.credit_suisse.drawing.console

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

import org.junit.Assert.assertEquals
import java.lang.IllegalArgumentException

class ConsoleCanvasTest {

    private lateinit var canvas: ConsoleCanvas

    @Test
    fun `test creation, happy path`() {
        canvas = ConsoleCanvas(1, 1, 10, 10)
        assertEquals(canvas.width, 10)
        assertEquals(canvas.height, 10)

        canvas = ConsoleCanvas(1, 1, 1, 1)
        assertEquals(canvas.width, 1)
        assertEquals(canvas.height, 1)

        canvas = ConsoleCanvas(-1, -1, 1, 1)
        assertEquals(canvas.width, 3)
        assertEquals(canvas.height, 3)
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

    @Test
    fun close() {
    }
}