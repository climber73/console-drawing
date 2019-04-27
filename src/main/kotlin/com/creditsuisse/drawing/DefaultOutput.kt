package com.creditsuisse.drawing

import java.io.BufferedWriter
import java.lang.Exception

class DefaultOutput(
    private val writer: BufferedWriter? = null
) : Output {

    override fun printError(e: Exception) {
        println("ERROR: ${e.message}")
//        e.printStackTrace()
    }

    override fun print(s: String) {
        if (writer == null) return
        writer.write(s)
        writer.flush()
    }

    override fun println(s: String) {
        if (writer == null) return
        writer.write(s)
        writer.newLine()
        writer.flush()
    }

    override fun close() {
        if (writer == null) return
        writer.close()
    }
}
