package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.*

class ConsoleShapeFactory : ShapeFactory {
    override fun shape(cmd: AddShapeCommand): Shape {
        return when (cmd) {
            is AddLine -> createLine(cmd)
            is AddRect -> createRect(cmd)
        }
    }

    // todo: common method (rect -> line -> point?)
    private fun createLine(c: AddLine): Line {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        require(c.x1 == c.x2 || c.y1 == c.y2) { "Only horizontal and vertical lines are supported currently" }
//        return if (c.x1 < c.x2 || c.y1 < c.y2) { // todo check!
           return Line(Point(c.x1, c.y1), Point(c.x2, c.y2))
//        } else {
//            Line(Point(c.x2, c.y2), Point(c.x1, c.y1))
//        }
    }

    private fun createRect(c: AddRect): Rect {
        require(c.x1 > 0 && c.y1 > 0 && c.x2 > 0 && c.y2 > 0) { "Coordinates must be positive" }
        return if (c.x1 < c.x2 || c.y1 < c.y2) {
            Rect(Point(c.x1, c.y1), Point(c.x2, c.y2))
        } else {
            Rect(Point(c.x2, c.y2), Point(c.x1, c.y1))
        }
    }
}
