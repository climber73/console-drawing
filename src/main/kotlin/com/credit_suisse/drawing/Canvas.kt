package com.credit_suisse.drawing

/*

 Example of canvas 6 x 3 (width = 6, height = 3):

          x
          123456
         --------
    y  1 |      |
       2 |      |
       3 |      |
         --------
*/

interface Canvas<T> : Iterable<Iterable<T>> {
    val width: Int
    val height: Int
    fun contain(p: Point): Boolean
    fun add(shape: Shape)
    fun bucketFill(fill: BucketFill)
    fun close()
}
