package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.*
import java.io.BufferedReader

class ConsoleApplication(
    private val input: BufferedReader,
    private val logger: Logger,
    private val parser: CommandLineParser = CommandLineParser(logger),
    private val factory: ShapeFactory = ConsoleShapeFactory()
) {

    private val renderer: CanvasRenderer<String> = ConsoleCanvasRenderer()
    private var canvas: Canvas? = null

    fun run() {
        while (true) {
            logger.print(renderer.render(canvas))
            logger.println("Enter command (H for help):")
            val line = input.readLine()
            try {
                when (val cmd = parser.parse(line)) {
                    is CreateCanvas -> createCanvas(cmd)
                    is AddShapeCommand -> addShape(cmd)
                    is BucketFill -> bucketFill(cmd)
                    is PrintHelp -> printHelp()
                    is Quit -> return
                }
            } catch (e: IllegalArgumentException) {
                logger.error(e.message)
                printHelp()
            }
        }
    }

    private fun printHelp() {
        parser.printHelp()
    }

    private fun createCanvas(cmd: CreateCanvas) {
        canvas = ConsoleCanvas(cmd.width, cmd.height)
    }

    private fun addShape(cmd: AddShapeCommand) {
        require(canvas != null) { "Canvas should be created before" }
        val shape = factory.shape(cmd)
        canvas?.add(shape)
    }

    private fun bucketFill(cmd: BucketFill) {
        require(canvas != null) { "Canvas should be created before" }
        canvas?.bucketFill(cmd)
    }
}
