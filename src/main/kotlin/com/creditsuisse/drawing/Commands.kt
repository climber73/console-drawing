package com.creditsuisse.drawing

interface Command

interface CreateCanvas : Command {
    val width: Int
    val height: Int
}

interface AddLine<C> : Command {
    val x1: Int
    val y1: Int
    val x2: Int
    val y2: Int
    val c: C
}

interface AddRect<C> : Command {
    val x1: Int
    val y1: Int
    val x2: Int
    val y2: Int
    val c: C
}

interface BucketFill<C> : Command {
    val x: Int
    val y: Int
    val c: C
}
