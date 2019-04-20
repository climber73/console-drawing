package com.credit_suisse.drawing

interface Command {
}

data class CreateCanvas(
    val width: Int,
    val height: Int
) : Command

data class NewLine(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
) : Command

data class NewRectangle(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
) : Command

data class BucketFill(
    val x: Int,
    val y: Int,
    val c: Char
) : Command

class Quit : Command

class NeedHelp : Command