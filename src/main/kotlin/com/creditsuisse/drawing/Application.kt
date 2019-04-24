package com.creditsuisse.drawing

/**
 * can be extended by UNDO ...
 */
interface Application<T, C> {

    fun createCanvas(cmd: CreateCanvas)

    fun addLine(cmd: AddLine<C>)

    fun addRect(cmd: AddRect<C>)

    fun bucketFill(cmd: BucketFill<C>)

    fun state(): T

}
