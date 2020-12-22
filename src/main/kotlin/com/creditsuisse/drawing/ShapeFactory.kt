package com.creditsuisse.drawing

class ShapeFactory<C> {

    fun line(x1: Int, y1: Int, x2: Int, y2: Int): Line {
        require(x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0) { "Coordinates must be positive" }
        require(x1 == x2 || y1 == y2) { "Currently only horizontal or vertical lines are supported" }
        return Line(Point(x1, y1), Point(x2, y2))
    }

    fun rect(x1: Int, y1: Int, x2: Int, y2: Int): Rect {
        require(x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0) { "Coordinates must be positive" }
        return Rect(Point(x1, y1), Point(x2, y2))
    }
}
