package com.credit_suisse.drawing

interface Shape {
    fun isFit(c: Canvas): Boolean
//    fun points(): List<Point>
}

data class Point(
    val x: Int,
    val y: Int
) : Shape {
//    override fun points() = listOf(this)
    override fun isFit(c: Canvas) = c.contain(this)
}

data class Line(
    val p1: Point,
    val p2: Point
) : Shape {
//    override fun points(): List<Point> {
//        return listOf(this)
//    }
    override fun isFit(c: Canvas) = c.contain(p1) && c.contain(p2)
}

data class Rect(
    val p1: Point,
    val p2: Point
) : Shape {
//    override fun points(): List<Point> {
//        return listOf(this)
//    }
    override fun isFit(c: Canvas) = c.contain(p1) && c.contain(p2)
}
