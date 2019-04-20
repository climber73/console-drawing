package com.credit_suisse.drawing

import com.credit_suisse.drawing.cli.CommandLineLoop
import com.credit_suisse.drawing.cli.CommandLineParser

fun main() {
    val parser = CommandLineParser()
    CommandLineLoop(parser).run()
}
