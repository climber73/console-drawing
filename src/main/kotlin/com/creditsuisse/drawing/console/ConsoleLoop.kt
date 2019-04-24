package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*
import java.io.BufferedReader

class ConsoleLoop(
    private val input: BufferedReader,
    private val output: Output,
    private val parser: CommandLineParser = CommandLineParser(output)
) {

    private val app = ConsoleApplication()

    fun run() {
        while (true) {
            output.print(app.state())
            output.println("Enter command (H for help):")
            val line = input.readLine()
            var cmd: Command = PrintHelp()
            try {
                cmd = parser.parse(line)
                when (cmd) {
                    is CreateConsoleCanvas -> app.createCanvas(cmd)
                    is AddConsoleLine -> app.addLine(cmd)
                    is AddConsoleRect -> app.addRect(cmd)
                    is ConsoleBucketFill -> app.bucketFill(cmd)
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
}
