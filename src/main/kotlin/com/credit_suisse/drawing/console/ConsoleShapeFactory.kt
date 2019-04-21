package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.*

class ConsoleShapeFactory : ShapeFactory<Char> {
    override fun shape(cmd: AddShapeCommand): Shape<Char> {
        return when (cmd) {
            is AddLine -> createLine(cmd)
            is AddRect -> createRect(cmd)
        }
    }

    // todo: common method (rect -> line -> point?)
    private fun createLine(c: AddLine): Line<Char> {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        require(c.x1 == c.x2 || c.y1 == c.y2) { "Only horizontal and vertical lines are supported currently" }
        return if (c.x1 < c.x2 || c.y1 < c.y2) {
            ConsoleLine(ConsolePoint(c.x1, c.y1), ConsolePoint(c.x2, c.y2))
        } else {
            ConsoleLine(ConsolePoint(c.x2, c.y2), ConsolePoint(c.x1, c.y1))
        }
    }

    private fun createRect(c: AddRect): Rect<Char> {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        return if (c.x1 < c.x2 || c.y1 < c.y2) {
            ConsoleRect(ConsolePoint(c.x1, c.y1), ConsolePoint(c.x2, c.y2))
        } else {
            ConsoleRect(ConsolePoint(c.x2, c.y2), ConsolePoint(c.x1, c.y1))
        }
    }
}
