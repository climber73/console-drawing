package com.credit_suisse.drawing

interface Shape<C, N : Number> {
    fun isFit(c: Canvas<C, N>): Boolean
    fun points(): List<Point<C, N>>
}

interface Point<C, N : Number> : Shape<C, N> {
    val x: N
    val y: N
    val attr: C
    override fun isFit(c: Canvas<C, N>) = c.contain(this)
    override fun points() = listOf(this)
}

interface Line<C, N : Number> : Shape<C, N> {
    val p1: Point<C, N>
    val p2: Point<C, N>
    override fun isFit(c: Canvas<C, N>) = c.contain(p1) && c.contain(p2)
}

interface Rect<C, N : Number> : Shape<C, N> {
    val p1: Point<C, N>
    val p2: Point<C, N>
    override fun isFit(c: Canvas<C, N>) = c.contain(p1) && c.contain(p2)
}
