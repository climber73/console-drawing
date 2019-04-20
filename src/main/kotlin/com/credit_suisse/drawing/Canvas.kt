package com.credit_suisse.drawing

interface Canvas {
    val width: Int
    val height: Int
    fun newLine(x1: Int, y1: Int, x2: Int, y2: Int)
    fun newRectangle(x1: Int, y1: Int, x2: Int, y2: Int)
    fun bucketFill(x: Int, y: Int, c: Char)
    fun close()
}