package com.creditsuisse.drawing

interface Point<C> {
    val x: Int
    val y: Int
    val color: C
}

interface Shape<P : Point<C>, C> {
    val color: C
    fun points(): List<P>
    fun contains(p: P): Boolean
}

interface Line<P : Point<C>, C> : Shape<P, C> {
    val p1: P
    val p2: P
}

interface Rect<P : Point<C>, C> : Shape<P, C> {
    val p1: P
    val p2: P
}
