package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Line
import com.creditsuisse.drawing.Point
import com.creditsuisse.drawing.Rect

class ConsoleProjectionFactory {

    fun points(shape: Line): List<Point> {
        return when {
            shape.xMin() == shape.xMax() -> (shape.yMin()..shape.yMax()).map { y -> Point(shape.xMin(), y) }
            shape.yMin() == shape.yMax() -> (shape.xMin()..shape.xMax()).map { x -> Point(x, shape.yMin()) }
            else -> throw NotImplementedError()
        }
    }

    fun points(shape: Rect): List<Point> {
        return (shape.yMin()..shape.yMax()).flatMap { y ->
            (shape.xMin()..shape.xMax()).map { x ->
                Point(x, y)
            }
        }
    }
}
