package com.credit_suisse.drawing.cli

import com.credit_suisse.drawing.NeedHelp
import com.credit_suisse.drawing.Quit
import java.io.BufferedReader
import java.io.InputStreamReader

class CommandLineLoop(
    private val parser: CommandLineParser
) {

    fun run() {
        val streamReader = InputStreamReader(System.`in`)
        BufferedReader(streamReader).use { reader ->
            while (true) {
                val line = reader.readLine()
                val cmd = parser.parse(line)
                when (cmd) {
                    is NeedHelp -> parser.printHelp()
                    is Quit -> return
                    else -> println(cmd)
                }
            }
        }
        // todo interruption?
    }

//    fun run() {
//        val streamReader = InputStreamReader(System.`in`)
//        BufferedReader(streamReader).use { reader ->
//            var line = reader.readLine()
//            var cmd = parser.parse(line)
//            while (cmd !is Quit) {
//                if (cmd is NeedHelp) {
//                    parser.printHelp()
//                } else {
//                    println(cmd)
//                }
//                line = reader.readLine()
//                cmd = parser.parse(line)
//            }
//        }
//    }
}