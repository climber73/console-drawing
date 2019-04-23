package com.creditsuisse.drawing.console

import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

internal operator fun Char.times(n: Int) = this.toString().repeat(n)
internal operator fun String.times(n: Int) = this.repeat(n)

internal class ConsoleCanvasRendererTest {

    private lateinit var canvas: ConsoleCanvas
    private var renderer: ConsoleCanvasRenderer = ConsoleCanvasRenderer()

    @Test
    fun `render null`() {
        assertThat(renderer.render(null)).isEqualTo("")
    }

    @Test
    fun `render empty canvas, size 1x1`() {
        canvas = ConsoleCanvas(1, 1)
        assertThat(renderer.render(canvas)).isEqualTo(
            """
                ---
                | |
                ---

                """.trimIndent()
        )
    }

    @Test
    fun `render empty canvas, size 2x1`() {
        canvas = ConsoleCanvas(2, 1)
        assertThat(renderer.render(canvas)).isEqualTo(
            """
                ----
                |  |
                ----

                """.trimIndent()
        )
    }

    @Test
    fun `render empty canvas, size 2x2`() {
        canvas = ConsoleCanvas(2, 2)
        assertThat(renderer.render(canvas)).isEqualTo(
            """
                ----
                |  |
                |  |
                ----

                """.trimIndent()
        )
    }

    @Test
    fun `render empty canvas, size 80x20`() {
        canvas = ConsoleCanvas(80, 20)

        assertThat(renderer.render(canvas)).isEqualTo(
            listOf(
                '-' * 82 + '\n',
                ('|' + ' ' * 80 + '|' + '\n') * 20,
                '-' * 82 + '\n'
            ).joinToString("")
        )
    }

    @Test
    fun `render canvas with lines`() {
        canvas = ConsoleCanvas(3, 3)
        canvas.add(ConsoleLine(1, 1, 3, 1))
        canvas.add(ConsoleLine(2, 1, 2, 3))
        assertThat(renderer.render(canvas)).isEqualTo(
            """
                -----
                |xxx|
                | x |
                | x |
                -----

                """.trimIndent()
        )
    }

    @Test
    fun `render canvas with rectangles`() {
        canvas = ConsoleCanvas(7, 7)
        canvas.add(ConsoleRect(1, 1, 5, 5))
        canvas.add(ConsoleRect(3, 3, 7, 7))
        assertThat(renderer.render(canvas)).isEqualTo(
            """
                ---------
                |xxxxx  |
                |x   x  |
                |x xxxxx|
                |x x x x|
                |xxxxx x|
                |  x   x|
                |  xxxxx|
                ---------

                """.trimIndent()
        )
    }
}