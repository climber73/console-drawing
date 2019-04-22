package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Line
import com.creditsuisse.drawing.Point
import com.creditsuisse.drawing.Rect
import com.creditsuisse.drawing.Shape

const val BLANK_COLOR = ' '
const val DEFAULT_COLOR = 'x'

data class ConsolePoint(
    override val x: Int,
    override val y: Int,
    override val color: Char = BLANK_COLOR
) : Point<Char>

typealias ConsoleShape = Shape<ConsolePoint, Char>

data class ConsoleLine(
    override val x1: Int,
    override val y1: Int,
    override val x2: Int,
    override val y2: Int,
    override val color: Char = DEFAULT_COLOR
) : Line<ConsolePoint, Char> {

    init {
        require(x1 == x2 || y1 == y2) { "Only horizontal and vertical lines are supported currently" }
    }

    private fun xRange() = if (x1 < x2) x1..x2 else x2..x1
    private fun yRange() = if (y1 < y2) y1..y2 else y2..y1

    override fun contains(p: ConsolePoint) =
        p.x in xRange() && p.y in yRange()

    override fun points(): List<ConsolePoint> {
        return if (x1 == x2) {
            yRange().map { y -> ConsolePoint(x1, y) }
        } else {
            xRange().map { x -> ConsolePoint(x, y1) }
        }
    }
}

data class ConsoleRect(
    override val x1: Int,
    override val y1: Int,
    override val x2: Int,
    override val y2: Int,
    override val color: Char = DEFAULT_COLOR
) : Rect<ConsolePoint, Char> {

    private fun xRange() = if (x1 < x2) x1..x2 else x2..x1
    private fun yRange() = if (y1 < y2) y1..y2 else y2..y1

    override fun contains(p: ConsolePoint) =
        p.x in xRange() && p.y in yRange()

    override fun points(): List<ConsolePoint> {
        val yList = yRange().map { y -> listOf(ConsolePoint(x1, y), ConsolePoint(x2, y)) }
        val xList = xRange().map { x -> listOf(ConsolePoint(x, y1), ConsolePoint(x, y2)) }
        return (yList + xList).flatten()
    }
}
