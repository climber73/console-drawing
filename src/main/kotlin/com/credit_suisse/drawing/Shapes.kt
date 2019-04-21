package com.credit_suisse.drawing

interface Shape<C> {
    fun isFit(c: Canvas<C>): Boolean
    fun points(): List<Point<C>>
}

interface Point<C> : Shape<C> {
    val x: Int
    val y: Int
    override fun isFit(c: Canvas<C>) = c.contain(this)
    override fun points() = listOf(this)
}

interface Line<C> : Shape<C> {
    val p1: Point<C>
    val p2: Point<C>
    override fun isFit(c: Canvas<C>) = c.contain(p1) && c.contain(p2)
}

interface Rect<C> : Shape<C> {
    val p1: Point<C>
    val p2: Point<C>
    override fun isFit(c: Canvas<C>) = c.contain(p1) && c.contain(p2)
}
