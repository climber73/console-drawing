package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Command
import com.creditsuisse.drawing.Logger
import java.lang.NumberFormatException

val SPACE_REGEX = "\\s+".toRegex()

class CommandLineParser(
    private val logger: Logger
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
            CreateCanvas::class.java -> logger.println("$HELP_HEADER\n$C")
            AddLine::class.java -> logger.println("$HELP_HEADER\n$L")
            AddRect::class.java -> logger.println("$HELP_HEADER\n$R")
            BucketFill::class.java -> logger.println("$HELP_HEADER\n$B")
            else -> logger.println(FULL_HELP)
        }
    }

    private fun parseCreateCanvas(args: List<String>): Command {
        if (args.size != 2) return PrintHelp(CreateCanvas::class.java)
        return try {
            val w = Integer.parseInt(args[0])
            val h = Integer.parseInt(args[1])
            CreateCanvas(w, h)
        } catch (e: NumberFormatException) {
            logger.error(e.message)
            PrintHelp(CreateCanvas::class.java)
        }
    }

    private fun parseAddLine(args: List<String>): Command {
        if (args.size != 4) return PrintHelp(AddLine::class.java)
        return try {
            val x1 = Integer.parseInt(args[0])
            val y1 = Integer.parseInt(args[1])
            val x2 = Integer.parseInt(args[2])
            val y2 = Integer.parseInt(args[3])
            AddLine(x1, y1, x2, y2)
        } catch (e: NumberFormatException) {
            logger.error(e.message)
            PrintHelp(AddLine::class.java)
        }
    }

    private fun parseAddRect(args: List<String>): Command {
        if (args.size != 4) return PrintHelp(AddRect::class.java)
        return try {
            val x1 = Integer.parseInt(args[0])
            val y1 = Integer.parseInt(args[1])
            val x2 = Integer.parseInt(args[2])
            val y2 = Integer.parseInt(args[3])
            AddRect(x1, y1, x2, y2)
        } catch (e: NumberFormatException) {
            logger.error(e.message)
            PrintHelp(AddRect::class.java)
        }
    }

    private fun parseBucketFill(args: List<String>): Command {
        if (args.size != 3 || args[2].length != 1) return PrintHelp(BucketFill::class.java)
        // todo check if char printable
        val c = args[2].first()
        return try {
            val x = Integer.parseInt(args[0])
            val y = Integer.parseInt(args[1])
            BucketFill(x, y, c)
        } catch (e: NumberFormatException) {
            logger.error(e.message)
            PrintHelp(BucketFill::class.java)
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