package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*

private const val BLANK_COLOR = ' '

class ConsoleCanvas(
    w: Int,
    h: Int,
    blankColor: Char = BLANK_COLOR,
    converter: ShapeConverter = ConsoleShapeConverter()
) : AbstractCanvas<Char>(
    width = w,
    height = h,
    converter = converter,
    state = Array(h * w) { blankColor }
)
