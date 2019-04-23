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
            .hasMessageStartingWith("Canvas width should be")
        assertThatThrownBy { ConsoleCanvas(0, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Canvas width should be")
        assertThatThrownBy { ConsoleCanvas(81, 21) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Canvas width should be")
    }

    @Test
    fun `contains point`() {
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
    fun `contains line`() {
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
    fun `contains rect`() {
        canvas = ConsoleCanvas(10, 10)
        assertThat(canvas.contains(ConsoleRect(1, 1, 10, 10))).isTrue()
        assertThat(canvas.contains(ConsoleRect(10, 10, 1, 1))).isTrue()

        assertThat(canvas.contains(ConsoleRect(0, 0, 10, 10))).isFalse()
        assertThat(canvas.contains(ConsoleRect(10, 10, 0, 0))).isFalse()

        assertThat(canvas.contains(ConsoleRect(-1, -1, -10, -10))).isFalse()
        assertThat(canvas.contains(ConsoleRect(-10, -10, -1, -1))).isFalse()
    }

    @Test
    fun `add line, happy path`() {
        canvas = ConsoleCanvas(10, 10)
        canvas.add(ConsoleLine(1, 1, 1, 10))
        assertThat(canvas.chunked(10).map { it.first() } ).containsOnly('x')
        canvas.add(ConsoleLine(1, 10, 10, 10))
        assertThat(canvas.toList().slice(90..99)).containsOnly('x')
        canvas.add(ConsoleLine(10, 10, 10, 1))
        assertThat(canvas.chunked(10).map { it.last() } ).containsOnly('x')
        canvas.add(ConsoleLine(1, 1, 10, 1))
        assertThat(canvas.toList().slice(0..9)).containsOnly('x')
    }

    @Test
    fun `add line, does not fit canvas`() {
        canvas = ConsoleCanvas(10, 10)
        assertThatThrownBy { canvas.add(ConsoleLine(0, 0, 0, 10)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleLine(0, 10, 10, 10)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleLine(10, 10, 10, 0)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleLine(0, 0, 10, 0)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
    }

    @Test
    fun `add rect, happy path`() {
        canvas = ConsoleCanvas(10, 10)
        canvas.add(ConsoleRect(1, 1, 10, 10))
        assertThat(canvas.chunked(10).flatMap { listOf(it.first(), it.last()) } ).containsOnly('x')
        assertThat(canvas.toList().slice(90..99)).containsOnly('x')
        assertThat(canvas.toList().slice(0..9)).containsOnly('x')
    }

    @Test
    fun `add rect, does not fit canvas`() {
        canvas = ConsoleCanvas(10, 10)
        assertThatThrownBy { canvas.add(ConsoleRect(0, 0, 10, 10)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleRect(1, 0, 10, 10)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleRect(0, 1, 10, 10)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleRect(1, 1, 11, 11)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleRect(1, 1, 10, 11)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
        assertThatThrownBy { canvas.add(ConsoleRect(1, 1, 11, 10)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
    }

    @Test
    fun `bucketFill, happy path`() {
        canvas = ConsoleCanvas(5, 5)
        canvas.bucketFill(ConsolePoint(1, 1), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                ccccc
                ccccc
                ccccc
                ccccc
                ccccc
            """.trimIndent())

        canvas = ConsoleCanvas(5, 5, '.')
        canvas.add(ConsoleLine(1, 3, 5, 3))
        canvas.bucketFill(ConsolePoint(1, 1), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                ccccc
                ccccc
                xxxxx
                .....
                .....
            """.trimIndent())
    }

    @Test
    fun `bucketFill, point does not fit canvas`() {
        canvas = ConsoleCanvas(10, 10)
        assertThatThrownBy { canvas.bucketFill(ConsolePoint(11, 11), 'c') }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
    }
}