package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Point
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

import java.lang.IllegalArgumentException

class ConsoleCanvasTest {

    private lateinit var canvas: ConsoleCanvas

    @Test
    fun `test creation 80x20`() {
        canvas = ConsoleCanvas(80, 20)
        assertThat(canvas.width).isEqualTo(80)
        assertThat(canvas.height).isEqualTo(20)
    }

    @Test
    fun `test creation 1x1`() {
        canvas = ConsoleCanvas(1, 1)
        assertThat(canvas.width).isEqualTo(1)
        assertThat(canvas.height).isEqualTo(1)
    }

    @Test
    fun `test creation, negative dims`() {
        assertThatThrownBy { ConsoleCanvas(-1, -1) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Canvas width should be")
    }

    @Test
    fun `test creation, wrong dims, 0x0`() {
        assertThatThrownBy { ConsoleCanvas(0, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Canvas width should be")
    }

    @Test
    fun `test creation, wrong dims, 81x21`() {
        assertThatThrownBy { ConsoleCanvas(81, 21) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Canvas width should be")
    }

    @Test
    fun `draw line, happy path`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawLine(1, 1, 1, 5)
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo(
                """
                x....
                x....
                x....
                x....
                x....
            """.trimIndent()
            )
        canvas.drawLine(1, 5, 5, 5)
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo(
                """
                x....
                x....
                x....
                x....
                xxxxx
            """.trimIndent()
            )
        canvas.drawLine(5, 5, 5, 1)
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo(
                """
                x...x
                x...x
                x...x
                x...x
                xxxxx
            """.trimIndent()
            )
        canvas.drawLine(1, 1, 5, 1)
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo(
                """
                xxxxx
                x...x
                x...x
                x...x
                xxxxx
            """.trimIndent()
            )
    }

    @Test
    fun `draw line, does not fit canvas`() {
        canvas = ConsoleCanvas(10, 10)
        assertThatThrownBy { canvas.drawLine(0, 0, 0, 10) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawLine(0, 10, 10, 10) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawLine(10, 10, 10, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawLine(0, 0, 10, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `draw rect, happy path`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawRect(1, 1, 5, 5)
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo(
                """
                xxxxx
                x...x
                x...x
                x...x
                xxxxx
            """.trimIndent()
            )
    }

    @Test
    fun `draw rect, does not fit canvas`() {
        canvas = ConsoleCanvas(10, 10)
        assertThatThrownBy { canvas.drawRect(0, 0, 10, 10) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawRect(1, 0, 10, 10) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawRect(0, 1, 10, 10) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawRect(1, 1, 11, 11) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawRect(1, 1, 10, 11) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { canvas.drawRect(1, 1, 11, 10) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `bucketFill, fill empty canvas`() {
        canvas = ConsoleCanvas(5, 5)
        canvas.bucketFill(Point(1, 1), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo(
                """
                ccccc
                ccccc
                ccccc
                ccccc
                ccccc
            """.trimIndent()
            )
    }

    @Test
    fun `bucketFill, fill top part of the canvas`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawLine(1, 3, 5, 3)
        canvas.bucketFill(Point(1, 1), 'c')
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
    fun `bucketFill, fill bottom part of the canvas`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawLine(1, 3, 5, 3)
        canvas.bucketFill(Point(1, 5), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                .....
                .....
                xxxxx
                ccccc
                ccccc
            """.trimIndent())
    }

    @Test
    fun `bucketFill, fill left part of the canvas`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawLine(3, 1, 3, 5)
        canvas.bucketFill(Point(1, 5), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                ccx..
                ccx..
                ccx..
                ccx..
                ccx..
            """.trimIndent())
    }

    @Test
    fun `bucketFill, fill right part of the canvas`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawLine(3, 1, 3, 5)
        canvas.bucketFill(Point(5, 5), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                ..xcc
                ..xcc
                ..xcc
                ..xcc
                ..xcc
            """.trimIndent())
    }

    @Test
    fun `bucketFill, fill the line`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawLine(3, 1, 3, 5)
        canvas.bucketFill(Point(3, 3), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                ..c..
                ..c..
                ..c..
                ..c..
                ..c..
            """.trimIndent())
    }

    @Test
    fun `bucketFill, fill the rectangle`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawRect(1, 1, 4, 4)
        canvas.bucketFill(Point(1, 1), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                cccc.
                c..c.
                c..c.
                cccc.
                .....
            """.trimIndent())
    }

    @Test
    fun `bucketFill, fill the area inside the rectangle`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawRect(1, 1, 4, 4)
        canvas.bucketFill(Point(2, 2), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                xxxx.
                xccx.
                xccx.
                xxxx.
                .....
            """.trimIndent())
    }

    @Test
    fun `bucketFill, fill the area outside the rectangle`() {
        canvas = ConsoleCanvas(5, 5, '.')
        canvas.drawRect(2, 2, 4, 4)
        canvas.bucketFill(Point(1, 1), 'c')
        assertThat(canvas.chunked(5).joinToString("\n") { it.joinToString("") })
            .isEqualTo("""
                ccccc
                cxxxc
                cx.xc
                cxxxc
                ccccc
            """.trimIndent())
    }

    @Test
    fun `bucketFill, point does not fit canvas`() {
        canvas = ConsoleCanvas(10, 10)
        assertThatThrownBy { canvas.bucketFill(Point(11, 11), 'c') }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageEndingWith("doesn't fit canvas")
    }
}