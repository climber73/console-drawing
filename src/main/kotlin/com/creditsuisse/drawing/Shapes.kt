package com.creditsuisse.drawing

data class Point(
    val x: Int,
    val y: Int
)// : Comparable<Point>
//{
//    override fun compareTo(other: Point): Int {
//        return if (x == other.x) {
//            Integer.compare(y, other.y)
//        } else {
//            Integer.compare(x, other.x)
//        }
//    }
//}

interface Shape {
    fun xMin(): Int
    fun xMax(): Int
    fun yMin(): Int
    fun yMax(): Int
}

/**
 *  Rectangle
 */
data class Rect(
    val p1: Point,
    val p2: Point
): Shape {

    override fun xMin() = if (p1.x <= p2.x) p1.x else p2.x

    override fun xMax() = if (p1.x > p2.x) p1.x else p2.x

    override fun yMin() = if (p1.y <= p2.y) p1.y else p2.y

    override fun yMax() = if (p1.y > p2.y) p1.y else p2.y

//    init {
//        require(p1.x <= p2.x && p1.y <= p2.y) { "" }
//    }
}

/**
 *  Line
 */
data class Line(
    val p1: Point,
    val p2: Point
) : Shape {
    override fun xMin() = if (p1.x <= p2.x) p1.x else p2.x

    override fun xMax() = if (p1.x > p2.x) p1.x else p2.x

    override fun yMin() = if (p1.y <= p2.y) p1.y else p2.y

    override fun yMax() = if (p1.y > p2.y) p1.y else p2.y
//    init {
//        require(p1.x <= p2.x) { "" }
//    }
}
