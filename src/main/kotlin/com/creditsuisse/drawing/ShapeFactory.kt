package com.creditsuisse.drawing

class ShapeFactory {
//    fun shape(cmd: AddShapeCommand): Shape

    fun line(x1: Int, y1: Int, x2: Int, y2: Int): Line {
        require(x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0) { "Coordinates must be positive" }
        return Line(Point(x1, y1), Point(x2, y2))
    }

    fun rect(x1: Int, y1: Int, x2: Int, y2: Int): Rect {
        require(x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0) { "Coordinates must be positive" }
        // todo min max....
        return Rect(Point(x1, y1), Point(x2, y2))
    }
}
