package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Command
import com.creditsuisse.drawing.Output
import java.lang.NumberFormatException

val SPACE_REGEX = "\\s+".toRegex()

class CommandLineParser(
    private val output: Output
) {

    fun parse(line: String): Command {
        val args = line.trim().split(SPACE_REGEX)
        if (args.isEmpty()) return PrintHelp()
        return when (args.first()) {
            "C" -> parseCreateCanvas(args.drop(1))
            "L" -> parseAddLine(args.drop(1))
            "R" -> parseAddRect(args.drop(1))
            "B" -> parseBucketFill(args.drop(1))
            "Q" -> Quit()
            else -> PrintHelp()
        }
    }

    fun printHelp(cmdType: Class<out Command>) {
        when (cmdType) {
            CreateConsoleCanvas::class.java -> output.println("$HELP_HEADER\n$C")
            AddConsoleLine::class.java -> output.println("$HELP_HEADER\n$L")
            AddConsoleRect::class.java -> output.println("$HELP_HEADER\n$R")
            ConsoleBucketFill::class.java -> output.println("$HELP_HEADER\n$B")
            else -> output.println(FULL_HELP)
        }
    }

    private fun parseCreateCanvas(args: List<String>): Command {
        if (args.size != 2) return PrintHelp(CreateConsoleCanvas::class.java)
        return try {
            val w = Integer.parseInt(args[0])
            val h = Integer.parseInt(args[1])
            CreateConsoleCanvas(w, h)
        } catch (e: NumberFormatException) {
            output.printError(e.message)
            PrintHelp(CreateConsoleCanvas::class.java)
        }
    }

    private fun parseAddLine(args: List<String>): Command {
        if (args.size != 4) return PrintHelp(AddConsoleLine::class.java)
        return try {
            val x1 = Integer.parseInt(args[0])
            val y1 = Integer.parseInt(args[1])
            val x2 = Integer.parseInt(args[2])
            val y2 = Integer.parseInt(args[3])
            AddConsoleLine(x1, y1, x2, y2)
        } catch (e: NumberFormatException) {
            output.printError(e.message)
            PrintHelp(AddConsoleLine::class.java)
        }
    }

    private fun parseAddRect(args: List<String>): Command {
        if (args.size != 4) return PrintHelp(AddConsoleRect::class.java)
        return try {
            val x1 = Integer.parseInt(args[0])
            val y1 = Integer.parseInt(args[1])
            val x2 = Integer.parseInt(args[2])
            val y2 = Integer.parseInt(args[3])
            AddConsoleRect(x1, y1, x2, y2)
        } catch (e: NumberFormatException) {
            output.printError(e.message)
            PrintHelp(AddConsoleRect::class.java)
        }
    }

    private fun parseBucketFill(args: List<String>): Command {
        if (args.size != 3 || args[2].length != 1) return PrintHelp(ConsoleBucketFill::class.java)
        val c = args[2].first()
        return try {
            val x = Integer.parseInt(args[0])
            val y = Integer.parseInt(args[1])
            ConsoleBucketFill(x, y, c)
        } catch (e: NumberFormatException) {
            output.printError(e.message)
            PrintHelp(ConsoleBucketFill::class.java)
        }
    }
}

private val HELP_HEADER = """
            |Command         Description
        """.trimMargin()
private val C = """
            |C w h           Create a new canvas of width w and height h.
        """.trimMargin()
private val L = """
            |L x1 y1 x2 y2   Create a new line from (x1,y1) to (x2,y2). Currently only
            |                horizontal or vertical lines are supported. Horizontal and vertical lines
            |                will be drawn using the 'x' character.
        """.trimMargin()
private val R = """
            |R x1 y1 x2 y2   Create a new rectangle, whose upper left corner is (x1,y1) and
            |                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
            |                using the 'x' character.
        """.trimMargin()
private val B = """
            |B x y c         Fill the entire area connected to (x,y) with "colour" c. The
            |                behavior of this is the same as that of the "bucket fill" tool in paint
            |                programs.
        """.trimMargin()
private val Q = """
            |Q               Quit the program.
        """.trimMargin()
private val FULL_HELP = "$HELP_HEADER\n$C\n$L\n$R\n$B\n$Q"