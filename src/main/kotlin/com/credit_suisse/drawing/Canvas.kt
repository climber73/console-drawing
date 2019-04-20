package com.credit_suisse.drawing

/*

 Example of canvas 9 x 4 (width = 9, height = 4):

          x
          123456789
         -----------
    y  1 |         |
       2 |         |
       3 |         |
       4 |         |
         -----------
*/

interface Canvas: Iterable<Iterable<Char>>{
    val width: Int
    val height: Int
    fun contain(p: Point): Boolean
    fun add(shape: Shape)
    fun bucketFill(fill: BucketFill)
    fun close()
}
