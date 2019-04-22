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

    private fun createLine(c: AddLine): ConsoleLine {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        return ConsoleLine(c.x1, c.y1, c.x2, c.y2)
    }

    private fun createRect(c: AddRect): ConsoleRect {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        return ConsoleRect(c.x1, c.y1, c.x2, c.y2)
    }
}
