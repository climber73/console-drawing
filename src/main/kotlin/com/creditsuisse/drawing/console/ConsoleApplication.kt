package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*
import java.io.BufferedReader

class ConsoleApplication(
    private val input: BufferedReader,
    private val output: Output,
    private val parser: CommandLineParser = CommandLineParser(output),
    private val factory: ShapeFactory = ShapeFactory()
) {

    private val renderer = ConsoleCanvasRenderer()
    private val projectionFactory = ConsoleProjectionFactory()

    fun run() {
        var canvas: Canvas<Char>? = null
        while (true) {
            output.print(renderer.render(canvas))
            output.println("Enter command (H for help):")
            val line = input.readLine()
            var cmd: Command = PrintHelp()
            try {
                cmd = parser.parse(line)
                when (cmd) {
                    is CreateCanvas -> canvas = createCanvas(cmd)
                    is AddLine -> drawLine(canvas, cmd)
                    is AddRect -> drawRect(canvas, cmd)
                    is BucketFill -> bucketFill(canvas, cmd)
                    is PrintHelp -> printHelp(cmd)
                    is Quit -> return
                }
            } catch (e: IllegalArgumentException) {
                output.printError(e.message)
                printHelp(PrintHelp(cmd::class.java))
            }
        }
    }

    private fun printHelp(cmd: PrintHelp) {
        parser.printHelp(cmd.cmdType)
    }

    private fun createCanvas(cmd: CreateCanvas): Canvas<Char> {
        return ConsoleCanvas(cmd.width, cmd.height)
    }

    private fun drawLine(canvas: Canvas<Char>?, cmd: AddLine) {
        require(canvas != null) { "Canvas should be created before" }
        val line = factory.line(cmd.x1, cmd.y1, cmd.x2, cmd.y2)
        val points = projectionFactory.points(line)
        for (p in points) {
            canvas.set(p, cmd.c)
        }
    }

    private fun drawRect(canvas: Canvas<Char>?, cmd: AddRect) {
        require(canvas != null) { "Canvas should be created before" }
        val rect = factory.rect(cmd.x1, cmd.y1, cmd.x2, cmd.y2)
        val points = projectionFactory.points(rect)
        for (p in points) {
            canvas.set(p, cmd.c)
        }
    }

    private fun bucketFill(canvas: Canvas<Char>?, cmd: BucketFill) {
        require(canvas != null) { "Canvas should be created before" }
        val p = Point(cmd.x, cmd.y)
        canvas.bucketFill(p, cmd.c)
    }
}
