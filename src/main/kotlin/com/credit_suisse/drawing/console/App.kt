package com.credit_suisse.drawing.console

fun main() {
    val parser = CommandLineParser()
    val shapeFactory = ConsoleShapeFactory()
    val consoleRenderer = ConsoleRenderer(System.out)
    consoleRenderer.use { renderer ->
        CommandLineLoop(parser, renderer, shapeFactory)
            .run()
    }
}
