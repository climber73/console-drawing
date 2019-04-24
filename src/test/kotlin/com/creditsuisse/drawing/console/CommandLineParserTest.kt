package com.creditsuisse.drawing.console

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CommandLineParserTest {
    // todo mockito? not to state trash
    private val writer = System.out.bufferedWriter()
    private val logger = ConsoleOutput(writer)
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
        assertCorrectParsing("C 1 2", CreateConsoleCanvas(1, 2))
        assertCorrectParsing("C  1  2", CreateConsoleCanvas(1, 2))
        assertCorrectParsing(" C  1  2 ", CreateConsoleCanvas(1, 2))
    }

    @Test
    fun `parse CreateCanvas, happy path`() {
        assertCorrectParsing("C 1 2", CreateConsoleCanvas(1, 2))
        assertCorrectParsing("C -1 2", CreateConsoleCanvas(-1, 2))
        assertCorrectParsing("C 1 -2", CreateConsoleCanvas(1, -2))
    }

    @Test
    fun `parse CreateCanvas, argument errors`() {
        assertCorrectParsing("C 1", PrintHelp(CreateConsoleCanvas::class.java))
        assertCorrectParsing("C 1 2 e", PrintHelp(CreateConsoleCanvas::class.java))
        assertCorrectParsing("C o 2", PrintHelp(CreateConsoleCanvas::class.java))
        assertCorrectParsing("C 1 o", PrintHelp(CreateConsoleCanvas::class.java))
        assertCorrectParsing("C 0.1 2", PrintHelp(CreateConsoleCanvas::class.java))
        assertCorrectParsing("C 1 0.2", PrintHelp(CreateConsoleCanvas::class.java))
    }

    @Test
    fun `parse AddLine, happy path`() {
        assertCorrectParsing("L 1 2 3 4", AddConsoleLine(1, 2, 3, 4))
        assertCorrectParsing("L -1 2 -3 4", AddConsoleLine(-1, 2, -3, 4))
    }

    @Test
    fun `parse AddLine, wrong arguments`() {
        assertCorrectParsing("L 1", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 2", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 2 3", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 2 3 4 e", PrintHelp(AddConsoleLine::class.java))

        assertCorrectParsing("L 1 2 3 o", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 2 o 4", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 o 3 4", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L o 2 3 4", PrintHelp(AddConsoleLine::class.java))

        assertCorrectParsing("L 0.1 2 3 4", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 0.2 3 4", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 2 0.3 4", PrintHelp(AddConsoleLine::class.java))
        assertCorrectParsing("L 1 2 3 0.4", PrintHelp(AddConsoleLine::class.java))
    }

    @Test
    fun `parse AddRect, happy path`() {
        assertCorrectParsing("R 1 2 3 4", AddConsoleRect(1, 2, 3, 4))
        assertCorrectParsing("R -1 2 -3 4", AddConsoleRect(-1, 2, -3, 4))
    }

    @Test
    fun `parse AddRect, wrong arguments`() {
        assertCorrectParsing("R 1", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 2", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 2 3", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 2 3 4 e", PrintHelp(AddConsoleRect::class.java))

        assertCorrectParsing("R 1 2 3 o", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 2 o 4", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 o 3 4", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R o 2 3 4", PrintHelp(AddConsoleRect::class.java))

        assertCorrectParsing("R 0.1 2 3 4", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 0.2 3 4", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 2 0.3 4", PrintHelp(AddConsoleRect::class.java))
        assertCorrectParsing("R 1 2 3 0.4", PrintHelp(AddConsoleRect::class.java))
    }

    @Test
    fun `parse BucketFill, happy path`() {
        assertCorrectParsing("B 1 2 o", ConsoleBucketFill(1, 2, 'o'))
        assertCorrectParsing("B -1 2 щ", ConsoleBucketFill(-1, 2, 'щ'))
    }

    @Test
    fun `parse BucketFill, wrong arguments`() {
        assertCorrectParsing("B 1", PrintHelp(ConsoleBucketFill::class.java))
        assertCorrectParsing("B 1 2", PrintHelp(ConsoleBucketFill::class.java))
        assertCorrectParsing("B 1 2 s e", PrintHelp(ConsoleBucketFill::class.java))

        assertCorrectParsing("B o 2 s", PrintHelp(ConsoleBucketFill::class.java))
        assertCorrectParsing("B 1 o s", PrintHelp(ConsoleBucketFill::class.java))

        assertCorrectParsing("B 0.1 2 s", PrintHelp(ConsoleBucketFill::class.java))
        assertCorrectParsing("B 1 0.2 s", PrintHelp(ConsoleBucketFill::class.java))
    }

    private fun assertCorrectParsing(from: String, to: Command) {
        cmd = parser.parse(from)
        assertThat(cmd).isEqualTo(to)
    }

    @Test
    fun printHelp() {
        // todo?
    }
}