package com.creditsuisse.drawing

/**
 *
 *  Application class
 *
 *  Encapsulates application's state (canvas). State can be changed by commands
 *  Another commands can be added later
 *
 */
interface Application<T, C> {

    fun createCanvas(width: Int, height: Int)

    fun addLine(line: Line, c: C)

    fun addRect(rect: Rect, c: C)

    fun bucketFill(p: Point, c: C)

    fun state(): T

}
