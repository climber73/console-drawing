package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Line
import com.creditsuisse.drawing.Point
import com.creditsuisse.drawing.Rect
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class ConsoleShapeConverterTest {

    val converter = ConsoleShapeConverter()

    @Test
    fun `convert line`() {
        line(1, 1, 1, 1).isConvertedTo(Point(1, 1))

        line(1, 1, 2, 1).isConvertedTo(Point(1, 1), Point(2, 1))
        line(1, 1, 3, 1).isConvertedTo(Point(1, 1), Point(2, 1), Point(3, 1))
        line(3, 1, 1, 1).isConvertedTo(Point(1, 1), Point(2, 1), Point(3, 1))

        line(1, 1, 1, 2).isConvertedTo(Point(1, 1), Point(1, 2))
        line(1, 1, 1, 3).isConvertedTo(Point(1, 1), Point(1, 2), Point(1, 3))
        line(1, 3, 1, 1).isConvertedTo(Point(1, 1), Point(1, 2), Point(1, 3))
    }

    @Test
    fun `convert rect`() {
        rect(1, 1, 1, 1).isConvertedTo(Point(1, 1))

        rect(1, 1, 2, 1).isConvertedTo(Point(1, 1), Point(2, 1))
        rect(1, 1, 3, 1).isConvertedTo(Point(1, 1), Point(2, 1), Point(3, 1))
        rect(3, 1, 1, 1).isConvertedTo(Point(1, 1), Point(2, 1), Point(3, 1))

        rect(1, 1, 1, 2).isConvertedTo(Point(1, 1), Point(1, 2))
        rect(1, 1, 1, 3).isConvertedTo(Point(1, 1), Point(1, 2), Point(1, 3))
        rect(1, 3, 1, 1).isConvertedTo(Point(1, 1), Point(1, 2), Point(1, 3))

        rect(1, 1, 3, 3).isConvertedTo(
            Point(1, 1), Point(2, 1), Point(3, 1),
            Point(1, 2),              Point(3, 2),
            Point(1, 3), Point(2, 3), Point(3, 3)
        )
        rect(3, 3, 1, 1).isConvertedTo(
            Point(1, 1), Point(2, 1), Point(3, 1),
            Point(1, 2),              Point(3, 2),
            Point(1, 3), Point(2, 3), Point(3, 3)
        )
    }

    private fun line(x1: Int, y1: Int, x2:Int, y2: Int) =
        Line(Point(x1, y1), Point(x2, y2))

    private fun rect(x1: Int, y1: Int, x2:Int, y2: Int) =
        Rect(Point(x1, y1), Point(x2, y2))

    private fun Line.isConvertedTo(vararg points: Point) {
        assertThat(converter.convert(this))
            .containsExactlyElementsOf(points.toList())
    }

    private fun Rect.isConvertedTo(vararg points: Point) {
        assertThat(converter.convert(this))
            .containsExactlyElementsOf(points.toList())
    }
}