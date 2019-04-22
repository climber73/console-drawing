package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.AddShapeCommand
import com.creditsuisse.drawing.Shape
import com.creditsuisse.drawing.ShapeFactory

class ConsoleShapeFactory : ShapeFactory<ConsolePoint, Char> {

    override fun shape(cmd: AddShapeCommand): Shape<ConsolePoint, Char> {
        return when (cmd) {
            is AddLine -> createLine(cmd)
            is AddRect -> createRect(cmd)
            else -> throw IllegalArgumentException("Don't know what to do with $cmd")
        }
    }

    // todo: common method (rect -> line -> point?)
    private fun createLine(c: AddLine): ConsoleLine {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        require(c.x1 == c.x2 || c.y1 == c.y2) { "Only horizontal and vertical lines are supported currently" }
        return if (c.x1 < c.x2 || c.y1 < c.y2) {
            ConsoleLine(ConsolePoint(c.x1, c.y1), ConsolePoint(c.x2, c.y2))
        } else {
            ConsoleLine(ConsolePoint(c.x2, c.y2), ConsolePoint(c.x1, c.y1))
        }
    }

    private fun createRect(c: AddRect): ConsoleRect {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        val x1: Int; val x2: Int
        val y1: Int; val y2: Int
        if (c.x1 < c.x2) {
            x1 = c.x1; x2 = c.x2
        } else {
            x2 = c.x1; x1 = c.x2
        }
        if (c.y1 < c.y2) {
            y1 = c.y1; y2 = c.y2
        } else {
            y2 = c.y1; y1 = c.y2
        }
        return ConsoleRect(ConsolePoint(x1, y1), ConsolePoint(x2, y2))
    }
}
