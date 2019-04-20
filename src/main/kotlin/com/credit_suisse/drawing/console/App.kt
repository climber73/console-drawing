package com.credit_suisse.drawing.console

fun main() {
    val parser = CommandLineParser()
    val shapeFactory = ConsoleShapeFactory()
    val consoleRenderer = ConsoleRenderer()
    consoleRenderer.use { renderer ->
        ConsoleLoop(parser, renderer, shapeFactory)
            .run()
    }
}
