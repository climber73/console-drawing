package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Canvas
import com.credit_suisse.drawing.Renderer
import java.io.OutputStream

const val HORIZONTAL_BORDER = '-'
const val VERTICAL_BORDER = '|'

class ConsoleRenderer(
    out: OutputStream = System.out
) : Renderer {

    override fun close() {
        writer.close()
    }

    private val writer = out.bufferedWriter()

    override fun render(c: Canvas?) {
        if (c == null) return
        writer.append(horizontalBorder(c))
        writer.newLine()
        //todo simplify:
        val yIterator = c.iterator()
        while (yIterator.hasNext()) {
            writer.append(VERTICAL_BORDER)
            val xIterator = yIterator.next().iterator()
            while (xIterator.hasNext()) {
                writer.append(xIterator.next())
            }
            writer.append(VERTICAL_BORDER)
            writer.newLine()
        }
        writer.append(horizontalBorder(c))
        writer.newLine()
        writer.flush()
    }

    private fun horizontalBorder(c: Canvas) = HORIZONTAL_BORDER * (c.width + 2)

    private operator fun Char.times(n: Int) = this.toString().repeat(n)
}