package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Logger
import java.io.BufferedWriter

class ConsoleLogger(
    private val writer: BufferedWriter
) : Logger {

    override fun info(s: String?) {
        if (s == null) return
        println("INFO: $s")
    }

    override fun error(s: String?) {
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

    override fun print(c: Char) {
        writer.append(c)
        writer.flush()
    }

    override fun println(c: Char) {
        writer.append(c)
        writer.newLine()
        writer.flush()
    }

    override fun close() {
        writer.close()
    }
}
