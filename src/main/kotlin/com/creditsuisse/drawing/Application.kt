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

    fun createCanvas(cmd: CreateCanvas)

    fun addLine(cmd: AddLine<C>)

    fun addRect(cmd: AddRect<C>)

    fun bucketFill(cmd: BucketFill<C>)

    fun state(): T

}
