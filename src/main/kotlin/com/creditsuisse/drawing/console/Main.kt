package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Output
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    input().use { input ->
        output().use { output ->
            val loop = ConsoleApplication(input, output)
            loop.run()
        }
    }
}

private fun input(): BufferedReader {
    val streamReader = InputStreamReader(System.`in`)
    return BufferedReader(streamReader)
}

private fun output(): Output {
    val writer = System.out.bufferedWriter()
    return ConsoleOutput(writer)
}

