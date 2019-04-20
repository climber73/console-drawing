package com.credit_suisse.drawing.cli

import com.credit_suisse.drawing.*
import java.lang.NumberFormatException

class CommandLineParser {

    fun parse(line: String): Command {
        // todo test trim
        val args = line.trim().split(" ")
        if (args.isEmpty()) return NeedHelp()
        return when (args.first()) {
            "C" -> parseCreateCanvas(args.drop(1))
            "L" -> parseNewLine(args.drop(1))
            "R" -> parseNewRectangle(args.drop(1))
            "B" -> parseBucketFill(args.drop(1))
            "Q" -> Quit()
            else -> NeedHelp()
        }
    }

    fun printHelp() {
        // todo write to smth general
        val s = """
            |Command         Description
            |C w h           Should create a new canvas of width w and height h.
            |L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only
            |                horizontal or vertical lines are supported. Horizontal and vertical lines
            |                will be drawn using the 'x' character.
            |R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and
            |                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
            |                using the 'x' character.
            |B x y c         Should fill the entire area connected to (x,y) with "colour" c. The
            |                behavior of this is the same as that of the "bucket fill" tool in paint
            |                programs.
            |Q               Should quit the program.
        """.trimIndent()
        println(s)
    }

    private fun parseCreateCanvas(args: List<String>): Command {
        if (args.size != 2) return NeedHelp()
        return try {
            val w = Integer.parseInt(args[0])
            val h = Integer.parseInt(args[1])
            CreateCanvas(w, h)
        } catch (e: NumberFormatException) {
            // todo log error
            NeedHelp()
        }
    }

    private fun parseNewLine(args: List<String>): Command {
        if (args.size != 4) return NeedHelp()
        return try {
            val x1 = Integer.parseInt(args[0])
            val y1 = Integer.parseInt(args[1])
            val x2 = Integer.parseInt(args[2])
            val y2 = Integer.parseInt(args[3])
            // todo add check (vertical/horizontal)
            NewLine(x1, y1, x2, y2)
        } catch (e: NumberFormatException) {
            // todo log error
            NeedHelp()
        }
    }

    private fun parseNewRectangle(args: List<String>): Command {
        if (args.size != 4) return NeedHelp()
        return try {
            val x1 = Integer.parseInt(args[0])
            val y1 = Integer.parseInt(args[1])
            val x2 = Integer.parseInt(args[2])
            val y2 = Integer.parseInt(args[3])
            NewRectangle(x1, y1, x2, y2)
        } catch (e: NumberFormatException) {
            // todo log error
            NeedHelp()
        }
    }

    private fun parseBucketFill(args: List<String>): Command {
        if (args.size != 3) return NeedHelp()
        if (args[2].length != 1) return NeedHelp()
        // todo check if char printable
        val c = args[2].first()
        return try {
            val x = Integer.parseInt(args[0])
            val y = Integer.parseInt(args[1])
            BucketFill(x, y, c)
        } catch (e: NumberFormatException) {
            // todo log error
            NeedHelp()
        }
    }
}