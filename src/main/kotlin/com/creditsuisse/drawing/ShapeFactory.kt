package com.creditsuisse.drawing

class ShapeFactory<C> {

    fun line(cmd: AddLine<C>): Line {
        require(cmd.x1 > 0 && cmd.y1 > 0 && cmd.x2 > 0 && cmd.y2 > 0) { "Coordinates must be positive" }
        return Line(Point(cmd.x1, cmd.y1), Point(cmd.x2, cmd.y2))
    }

    fun rect(cmd: AddRect<C>): Rect {
        require(cmd.x1 > 0 && cmd.y1 > 0 && cmd.x2 > 0 && cmd.y2 > 0) { "Coordinates must be positive" }
        return Rect(Point(cmd.x1, cmd.y1), Point(cmd.x2, cmd.y2))
    }
}
