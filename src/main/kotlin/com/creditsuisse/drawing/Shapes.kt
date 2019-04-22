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

interface Rect<P : Point<C>, C> : Shape<P, C> {
    val x1: Int
    val y1: Int
    val x2: Int
    val y2: Int
}

interface Line<P : Point<C>, C> : Shape<P, C> {
    val x1: Int
    val y1: Int
    val x2: Int
    val y2: Int
}
