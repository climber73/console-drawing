package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Output
import java.io.BufferedWriter
import java.lang.Exception

class ConsoleOutput(
    private val writer: BufferedWriter
) : Output {

    override fun printError(e: Exception) {
        println("ERROR: ${e.message}")
        e.printStackTrace()
    }

    override fun print(s: String) {
        writer.write(s)
        writer.flush()
    }

    override fun println(s: String) {
        writer.write(s)
        writer.newLine()
        writer.flush()
    }

    override fun close() {
        writer.close()
    }
}
