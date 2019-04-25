package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*

class ConsoleShapeConverter : ShapeConverter {

    override fun convert(shape: Shape): List<Point> {
        return when(shape) {
            is Line -> points(shape)
            is Rect -> points(shape)
            else -> throw NotImplementedError()
        }
    }

    private fun points(line: Line): List<Point> {
        val xMin = line.xMin()
        val xMax = line.xMax()
        val yMin = line.yMin()
        val yMax = line.yMax()
        return when {
            xMin == xMax -> (yMin..yMax).map { y -> Point(xMin, y) }
            yMin == yMax -> (xMin..xMax).map { x -> Point(x, yMin) }
            else -> throw NotImplementedError()
        }
    }

    private fun points(rect: Rect): List<Point> {
        return (rect.yMin()..rect.yMax()).flatMap { y ->
            (rect.xMin()..rect.xMax()).map { x ->
                Point(x, y)
            }
        }
    }
}