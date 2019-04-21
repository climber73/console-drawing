package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Line
import com.credit_suisse.drawing.Point
import com.credit_suisse.drawing.Rect

const val BLANK_COLOR = ' '

data class ConsolePoint(
    override val x: Int,
    override val y: Int,
    override val attr: Char = BLANK_COLOR
) : Point<Char, Int>

data class ConsoleLine(
    override val p1: ConsolePoint,
    override val p2: ConsolePoint
) : Line<Char, Int> {
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
    override val p2: ConsolePoint
) : Rect<Char, Int> {
    override fun points(): List<ConsolePoint> {
        return when {
            p1.x == p2.x -> (p1.y..p2.y).map { y -> ConsolePoint(p1.x, y) }
            p1.y == p2.y -> (p1.x..p2.x).map { x -> ConsolePoint(x, p1.y) }
            else -> {
                val list = ArrayList<ConsolePoint>()
                (p1.y..p2.y).forEach { y ->
                    list.add(ConsolePoint(p1.x, y))
                    list.add(ConsolePoint(p2.x, y))
                }
                (p1.x..p2.x).forEach { x ->
                    list.add(ConsolePoint(x, p1.y))
                    list.add(ConsolePoint(x, p2.y))
                }
                list
            }
        }
    }
}
