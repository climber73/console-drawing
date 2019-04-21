package com.credit_suisse.drawing

/**
 *
 * A generic canvas
 *
 * Example of canvas 6 x 3 (width = 6, height = 3):
 *
 *      x
 *      123456
 *      --------
 *  y 1 |      |
 *    2 |      |
 *    3 |      |
 *      --------
 *
 * @param C the type of main attribute of points contained in the canvas (color for example)
 * @param N the numeric type of coordinates in the canvas
 *
 */

interface Canvas<C, N : Number> : Iterable<Iterable<C>> {

    val minX: N

    val minY: N

    val maxX: N

    val maxY: N

    val width: N

    val height: N

    // check if canvas contain point p
    fun contain(p: Point<C, N>): Boolean

    // add shape to canvas
    fun add(shape: Shape<C, N>)

    // fill the entire area connected to point p with "colour" of the point
    fun bucketFill(p: Point<C, N>)

    fun close()
}
