package com.creditsuisse.drawing.console

const val DEFAULT_COLOR = 'x'

interface Command

data class CreateCanvas(
    val width: Int,
    val height: Int
) : Command

data class AddLine(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
    val c: Char = DEFAULT_COLOR
) : Command

data class AddRect(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
    val c: Char = DEFAULT_COLOR
) : Command

data class BucketFill(
    val x: Int,
    val y: Int,
    val c: Char
) : Command

class Quit : Command

data class PrintHelp(
    val cmdType: Class<out Command> = Command::class.java
) : Command
