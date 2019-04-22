package com.credit_suisse.drawing

/**
 *
 * A generic canvas
 *
 * Example of canvas 6 x 3 (width = 6, height = 3):
 *
 *      x
 *       123456
 *      --------
 *  y 1 |      |
 *    2 |      |
 *    3 |      |
 *      --------
 *
 * @param P the type of points in the canvas
 * @param C the type of color of points in the canvas
 *
 */

interface Canvas<P : Point<C>, C> : Iterable<Iterable<P>> { // todo remove double iterable

    val width: Int

    val height: Int

    // check if canvas contain point p
    fun contains(p: P): Boolean

    // check if canvas contain shape sh
    fun contains(shape: Shape<P, C>): Boolean

    // add shape to canvas
    fun add(shape: Shape<P, C>)

    // fill the entire area connected to point p with color c
    fun bucketFill(p: P, c: C)

    fun close()
}
