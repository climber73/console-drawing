package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Command
import com.credit_suisse.drawing.CreateCanvas
import com.credit_suisse.drawing.PrintHelp
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CommandLineParserTest {
    private val writer = System.out.bufferedWriter()
    private val logger = ConsoleLogger(writer)
    private val parser = CommandLineParser(logger)
    private lateinit var cmd: Command

    @Test
    fun `parse empty string`() {
        cmd = parser.parse("")
        assertEquals(cmd, PrintHelp(Command::class.java))
        cmd = parser.parse(" ")
        assertEquals(cmd, PrintHelp(Command::class.java))
    }

    @Test
    fun `parse wrong first argument`() {
        cmd = parser.parse("wrong")
        assertEquals(cmd, PrintHelp(Command::class.java))
        cmd = parser.parse(" wrong")
        assertEquals(cmd, PrintHelp(Command::class.java))
    }

    @Test
    fun `parse CreateCanvas, happy path`() {
        cmd = parser.parse("C 10 5")
        assertEquals(cmd, CreateCanvas(10, 5))
        cmd = parser.parse("C  10  5")
        assertEquals(cmd, CreateCanvas(10, 5))
        cmd = parser.parse(" C  10  5 ")
        assertEquals(cmd, CreateCanvas(10, 5))

        // todo!
        cmd = parser.parse("C -10 5")
        assertEquals(cmd, CreateCanvas(-10, 5))
        cmd = parser.parse("C 10 -5")
        assertEquals(cmd, CreateCanvas(10, -5))
    }

    @Test
    fun `parse CreateCanvas, argument errors`() {
        cmd = parser.parse("C 10")
        assertEquals(cmd, PrintHelp(CreateCanvas::class.java))
        cmd = parser.parse("C 10 5 extraArg")
        assertEquals(cmd, PrintHelp(CreateCanvas::class.java))
        cmd = parser.parse("C notNum 5")
        assertEquals(cmd, PrintHelp(CreateCanvas::class.java))
        cmd = parser.parse("C 10 notNum")
        assertEquals(cmd, PrintHelp(CreateCanvas::class.java))
        cmd = parser.parse("C 1.2 5")
        assertEquals(cmd, PrintHelp(CreateCanvas::class.java))
        cmd = parser.parse("C 10 1.2")
        assertEquals(cmd, PrintHelp(CreateCanvas::class.java))
    }

    @Test
    fun printHelp() {
    }
}