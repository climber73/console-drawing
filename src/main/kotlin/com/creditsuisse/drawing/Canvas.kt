package com.creditsuisse.drawing

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
 * @param C the type of pattern of points in the canvas
 *
 */

interface Canvas<C> : Iterable<C> {

    val width: Int

    val height: Int

    /**
     *  set point on the canvas to the color c
     */
    fun set(p: Point, c: C)

    /**
     *  fill the entire area connected to point p with pattern c
     */
    fun bucketFill(p: Point, c: C)

    fun close()
}
