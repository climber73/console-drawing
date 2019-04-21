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
 * @param C the type of elements contained in the canvas
 *
 */
interface Canvas<C> : Iterable<Iterable<C>> {
    val width: Int
    val height: Int
    fun contain(p: Point<C>): Boolean
    fun add(shape: Shape<C>)
    fun bucketFill(fill: BucketFill)
    fun close()
}
