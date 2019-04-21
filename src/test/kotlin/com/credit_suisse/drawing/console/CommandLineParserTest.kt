package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Command
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CommandLineParserTest {
    // todo mockito? not to print trash
    private val writer = System.out.bufferedWriter()
    private val logger = ConsoleLogger(writer)
    private val parser = CommandLineParser(logger)
    private lateinit var cmd: Command

    @Test
    fun `parse empty string`() {
        assertCorrectParsing("", PrintHelp(Command::class.java))
        assertCorrectParsing(" ", PrintHelp(Command::class.java))
    }

    @Test
    fun `parse wrong first argument`() {
        assertCorrectParsing("wrong", PrintHelp(Command::class.java))
    }

    @Test
    fun `parse command with extra spaces`() {
        assertCorrectParsing("C 1 2", CreateCanvas(1, 2))
        assertCorrectParsing("C  1  2", CreateCanvas(1, 2))
        assertCorrectParsing(" C  1  2 ", CreateCanvas(1, 2))
    }

    @Test
    fun `parse CreateCanvas, happy path`() {
        assertCorrectParsing("C 1 2", CreateCanvas(1, 2))
        assertCorrectParsing("C -1 2", CreateCanvas(-1, 2))
        assertCorrectParsing("C 1 -2", CreateCanvas(1, -2))
    }

    @Test
    fun `parse CreateCanvas, argument errors`() {
        assertCorrectParsing("C 1", PrintHelp(CreateCanvas::class.java))
        assertCorrectParsing("C 1 2 e", PrintHelp(CreateCanvas::class.java))
        assertCorrectParsing("C o 2", PrintHelp(CreateCanvas::class.java))
        assertCorrectParsing("C 1 o", PrintHelp(CreateCanvas::class.java))
        assertCorrectParsing("C 0.1 2", PrintHelp(CreateCanvas::class.java))
        assertCorrectParsing("C 1 0.2", PrintHelp(CreateCanvas::class.java))
    }

    @Test
    fun `parse AddLine, happy path`() {
        assertCorrectParsing("L 1 2 3 4", AddLine(1, 2, 3, 4))
        assertCorrectParsing("L -1 2 -3 4", AddLine(-1, 2, -3, 4))
    }

    @Test
    fun `parse AddLine, wrong arguments`() {
        assertCorrectParsing("L 1", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 2", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 2 3", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 2 3 4 e", PrintHelp(AddLine::class.java))

        assertCorrectParsing("L 1 2 3 o", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 2 o 4", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 o 3 4", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L o 2 3 4", PrintHelp(AddLine::class.java))

        assertCorrectParsing("L 0.1 2 3 4", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 0.2 3 4", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 2 0.3 4", PrintHelp(AddLine::class.java))
        assertCorrectParsing("L 1 2 3 0.4", PrintHelp(AddLine::class.java))
    }

    @Test
    fun `parse AddRect, happy path`() {
        assertCorrectParsing("R 1 2 3 4", AddRect(1, 2, 3, 4))
        assertCorrectParsing("R -1 2 -3 4", AddRect(-1, 2, -3, 4))
    }

    @Test
    fun `parse AddRect, wrong arguments`() {
        assertCorrectParsing("R 1", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 2", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 2 3", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 2 3 4 e", PrintHelp(AddRect::class.java))

        assertCorrectParsing("R 1 2 3 o", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 2 o 4", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 o 3 4", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R o 2 3 4", PrintHelp(AddRect::class.java))

        assertCorrectParsing("R 0.1 2 3 4", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 0.2 3 4", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 2 0.3 4", PrintHelp(AddRect::class.java))
        assertCorrectParsing("R 1 2 3 0.4", PrintHelp(AddRect::class.java))
    }

    @Test
    fun `parse BucketFill, happy path`() {
        assertCorrectParsing("B 1 2 o", BucketFill(1, 2, 'o'))
        assertCorrectParsing("B -1 2 щ", BucketFill(-1, 2, 'щ'))
    }

    @Test
    fun `parse BucketFill, wrong arguments`() {
        assertCorrectParsing("B 1", PrintHelp(BucketFill::class.java))
        assertCorrectParsing("B 1 2", PrintHelp(BucketFill::class.java))
        assertCorrectParsing("B 1 2 s e", PrintHelp(BucketFill::class.java))

        assertCorrectParsing("B o 2 s", PrintHelp(BucketFill::class.java))
        assertCorrectParsing("B 1 o s", PrintHelp(BucketFill::class.java))

        assertCorrectParsing("B 0.1 2 s", PrintHelp(BucketFill::class.java))
        assertCorrectParsing("B 1 0.2 s", PrintHelp(BucketFill::class.java))
    }

    private fun assertCorrectParsing(from: String, to: Command) {
        cmd = parser.parse(from)
        assertEquals(cmd, to)
    }

    @Test
    fun printHelp() {
        // todo?
    }
}