package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*
import java.io.BufferedReader

class ConsoleLoop(
    private val input: BufferedReader,
    private val output: Output,
    private val parser: CommandLineParser = CommandLineParser(output),
    private val factory: ShapeFactory<Char> = ShapeFactory(),
    private val renderer: ConsoleCanvasRenderer = ConsoleCanvasRenderer()
) {

    private val app = ConsoleApplication(renderer)

    fun run() {
        while (true) {
            output.print(app.state())
            output.println("Enter command (H for help):")
            val line = input.readLine()
            var cmd: Command = PrintHelp()
            try {
                cmd = parser.parse(line)
                when (cmd) {
                    is CreateCanvas -> createCanvas(cmd)
                    is AddLine -> addLine(cmd)
                    is AddRect -> addRect(cmd)
                    is BucketFill -> bucketFill(cmd)
                    is PrintHelp -> printHelp(cmd)
                    is Quit -> return
                }
            } catch (e: IllegalArgumentException) {
                output.printError(e)
                printHelp(PrintHelp(cmd::class.java))
            }
        }
    }

    private fun createCanvas(cmd: CreateCanvas) {
        app.createCanvas(cmd.width, cmd.height)
    }

    private fun addLine(cmd: AddLine) {
        val line = factory.line(cmd.x1, cmd.y1, cmd.x2, cmd.y2)
        app.addLine(line, cmd.c)
    }

    private fun addRect(cmd: AddRect) {
        val rect = factory.rect(cmd.x1, cmd.y1, cmd.x2, cmd.y2)
        app.addRect(rect, cmd.c)
    }

    private fun bucketFill(cmd: BucketFill) {
        val p = Point(cmd.x, cmd.y)
        app.bucketFill(p, cmd.c)
    }

    private fun printHelp(cmd: PrintHelp) {
        parser.printHelp(cmd.cmdType)
    }
}
