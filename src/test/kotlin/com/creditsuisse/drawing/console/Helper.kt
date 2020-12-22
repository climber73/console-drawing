package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Canvas
import com.creditsuisse.drawing.Line
import com.creditsuisse.drawing.Point
import com.creditsuisse.drawing.Rect

internal fun Canvas<Char>.drawLine(x1: Int, y1: Int, x2: Int, y2: Int, c: Char = 'x') {
    draw(Line(Point(x1, y1), Point(x2, y2)), c)
}

internal fun Canvas<Char>.drawRect(x1: Int, y1: Int, x2: Int, y2: Int, c: Char = 'x') {
    draw(Rect(Point(x1, y1), Point(x2, y2)), c)
}
