package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Output
import java.io.BufferedWriter

class ConsoleOutput(
    private val writer: BufferedWriter
) : Output {

    override fun printError(s: String?) {
        if (s == null) return
        println("ERROR: $s")
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
