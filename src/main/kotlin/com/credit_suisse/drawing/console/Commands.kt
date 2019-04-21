package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.AddShapeCommand
import com.credit_suisse.drawing.Command

data class CreateCanvas(
    val width: Int,
    val height: Int
) : Command

data class AddLine(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
) : AddShapeCommand

data class AddRect(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
) : AddShapeCommand

data class BucketFill(
    val x: Int,
    val y: Int,
    val c: Char
) : Command

class Quit : Command

data class PrintHelp(
    val cmdType: Class<out Command> = Command::class.java
) : Command
