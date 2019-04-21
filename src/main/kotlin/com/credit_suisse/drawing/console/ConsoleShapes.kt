package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Line
import com.credit_suisse.drawing.Point
import com.credit_suisse.drawing.Rect
import com.credit_suisse.drawing.Shape

interface ConsoleShape : Shape

data class ConsolePoint(
    override val x: Int,
    override val y: Int
) : Point, ConsoleShape

data class ConsoleLine(
    override val p1: Point,
    override val p2: Point
) : Line, ConsoleShape {
    override fun points(): List<Point> {
        return if (p1.x == p2.x) {
            (p1.y..p2.y).map { y -> ConsolePoint(p1.x, y) }
        } else {
            (p1.x..p2.x).map { x -> ConsolePoint(x, p1.y) }
        }
    }
}

data class ConsoleRect(
    override val p1: Point,
    override val p2: Point
) : Rect, ConsoleShape {
    override fun points(): List<Point> {
        return when {
            p1.x == p2.x -> (p1.y..p2.y).map { y -> ConsolePoint(p1.x, y) }
            p1.y == p2.y -> (p1.x..p2.x).map { x -> ConsolePoint(x, p1.y) }
            else -> {
                val list = ArrayList<Point>()
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
