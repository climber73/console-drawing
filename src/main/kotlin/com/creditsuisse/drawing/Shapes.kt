package com.creditsuisse.drawing

data class Point(
    val x: Int,
    val y: Int
)

interface Shape {
    fun xMin(): Int
    fun xMax(): Int
    fun yMin(): Int
    fun yMax(): Int
}

data class Rect(
    val p1: Point,
    val p2: Point
): Shape {
    override fun xMin() = if (p1.x <= p2.x) p1.x else p2.x
    override fun xMax() = if (p1.x > p2.x) p1.x else p2.x
    override fun yMin() = if (p1.y <= p2.y) p1.y else p2.y
    override fun yMax() = if (p1.y > p2.y) p1.y else p2.y
}

data class Line(
    val p1: Point,
    val p2: Point
) : Shape {
    override fun xMin() = if (p1.x <= p2.x) p1.x else p2.x
    override fun xMax() = if (p1.x > p2.x) p1.x else p2.x
    override fun yMin() = if (p1.y <= p2.y) p1.y else p2.y
    override fun yMax() = if (p1.y > p2.y) p1.y else p2.y
}
