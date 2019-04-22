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
    override val p1: ConsolePoint,
    override val p2: ConsolePoint,
    override val color: Char = DEFAULT_COLOR
) : Line<ConsolePoint, Char> {

    override fun contains(p: ConsolePoint) =
        p.x in p1.x..p2.x && p.y in p1.y..p2.y

    override fun points(): List<ConsolePoint> {
        return if (p1.x == p2.x) {
            (p1.y..p2.y).map { y -> ConsolePoint(p1.x, y) }
        } else {
            (p1.x..p2.x).map { x -> ConsolePoint(x, p1.y) }
        }
    }
}

data class ConsoleRect(
    override val p1: ConsolePoint,
    override val p2: ConsolePoint,
    override val color: Char = DEFAULT_COLOR
) : Rect<ConsolePoint, Char> {

    override fun contains(p: ConsolePoint) =
        p.x in p1.x..p2.x && p.y in p1.y..p2.y

    override fun points(): List<ConsolePoint> {
        val list = ArrayList<ConsolePoint>()
        (p1.y..p2.y).forEach { y ->
            list.add(ConsolePoint(p1.x, y))
            list.add(ConsolePoint(p2.x, y))
        }
        (p1.x..p2.x).forEach { x ->
            list.add(ConsolePoint(x, p1.y))
            list.add(ConsolePoint(x, p2.y))
        }
        return list
    }
}
