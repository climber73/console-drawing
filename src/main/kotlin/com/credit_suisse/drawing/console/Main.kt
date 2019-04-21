package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Logger
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    input().use { input ->
        logger().use { logger ->
            val loop = ConsoleApplication(input, logger)
            loop.run()
        }
    }
}

private fun input(): BufferedReader {
    val streamReader = InputStreamReader(System.`in`)
    return BufferedReader(streamReader)
}

private fun logger(): Logger {
    val writer = System.out.bufferedWriter()
    return ConsoleLogger(writer)
}

