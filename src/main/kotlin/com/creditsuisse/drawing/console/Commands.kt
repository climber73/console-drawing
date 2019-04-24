package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*

const val DEFAULT_COLOR = 'x'

data class CreateConsoleCanvas(
    override val width: Int,
    override val height: Int
) : CreateCanvas

data class AddConsoleLine(
    override val x1: Int,
    override val y1: Int,
    override val x2: Int,
    override val y2: Int,
    override val c: Char = DEFAULT_COLOR
) : AddLine<Char>

data class AddConsoleRect(
    override val x1: Int,
    override val y1: Int,
    override val x2: Int,
    override val y2: Int,
    override val c: Char = DEFAULT_COLOR
) : AddRect<Char>

data class ConsoleBucketFill(
    override val x: Int,
    override val y: Int,
    override val c: Char
) : BucketFill<Char>

class Quit : Command

data class PrintHelp(
    val cmdType: Class<out Command> = Command::class.java
) : Command
