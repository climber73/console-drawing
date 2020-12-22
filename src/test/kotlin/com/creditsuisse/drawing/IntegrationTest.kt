package com.creditsuisse.drawing

import com.creditsuisse.drawing.console.ConsoleLoop
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test
import java.io.File
import java.io.FileWriter
import java.nio.file.Files

class IntegrationTest {

    private var tempFile: File = Files
        .createTempFile("output", ".txt")
        .also { println("Created temp file $it") }
        .toFile()

    @After
    fun after() {
        tempFile.deleteOnExit()
    }

    @Test
    fun run() {
        val inputFile = File("test-input.txt")
        val outputFile = File("test-output.txt")
        require(inputFile.exists()) { "File ${inputFile.absolutePath} does not exist" }
        require(outputFile.exists()) { "File ${outputFile.absolutePath} does not exist" }

        val input = inputFile.bufferedReader()
        val output = DefaultOutput(FileWriter(tempFile, false).buffered())
        ConsoleLoop(input, output).run()

        outputFile.bufferedReader().use { b1 ->
            tempFile.bufferedReader().use { b2 ->
                var s = b1.readLine()
                while (s != null) {
                    println(" >>> $s")
                    assertThat(s).isEqualTo(b2.readLine())
                    s = b1.readLine()
                }
                assertThat(b2.readLine()).isEqualTo(null)
            }
        }
    }

}