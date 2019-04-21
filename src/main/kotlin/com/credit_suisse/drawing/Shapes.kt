package com.credit_suisse.drawing

interface Shape {
    fun isFit(c: Canvas): Boolean
    fun points(): List<Point>
}

interface Point : Shape {
    val x: Int
    val y: Int
    override fun isFit(c: Canvas) = c.contain(this)
    override fun points() = listOf(this)
}

interface Line : Shape {
    val p1: Point
    val p2: Point
    override fun isFit(c: Canvas) = c.contain(p1) && c.contain(p2)
}

interface Rect : Shape {
    val p1: Point
    val p2: Point
    override fun isFit(c: Canvas) = c.contain(p1) && c.contain(p2)
}
