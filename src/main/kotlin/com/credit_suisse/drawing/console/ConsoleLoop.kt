package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.*
import java.io.BufferedReader
import java.io.InputStreamReader

class ConsoleLoop(
    private val parser: CommandLineParser,
    private val renderer: ConsoleRenderer,
    private val factory: ConsoleShapeFactory
) {

    private var canvas: Canvas? = null

    fun run() {
        val streamReader = InputStreamReader(System.`in`)
        BufferedReader(streamReader).use { reader ->
            while (true) {
                renderer.render(canvas)
                println("Enter command (H for help):")
                val line = reader.readLine()
                try {
                    when (val cmd = parser.parse(line)) {
                        is CreateCanvas -> createCanvas(cmd)
                        is AddShapeCommand -> addShape(cmd)
                        is BucketFill -> bucketFill(cmd)
                        is PrintHelp -> printHelp()
                        is Quit -> return
                    }
                } catch (e: IllegalArgumentException) {
                    // todo log error
                    println(e.message)
                    printHelp()
                }
            }
        }
        // todo interruption by ctrl-C?
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
