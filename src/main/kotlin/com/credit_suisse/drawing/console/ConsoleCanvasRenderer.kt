package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Canvas
import com.credit_suisse.drawing.CanvasRenderer
import java.lang.StringBuilder

const val HORIZONTAL_BORDER = '-'
const val VERTICAL_BORDER = '|'

class ConsoleCanvasRenderer : CanvasRenderer<String> {

    override fun close() {
    }

    override fun render(c: Canvas?): String {
        if (c == null) return ""
        val sb = StringBuilder()
        sb.append(horizontalBorder(c))
        //todo simplify:
        val yIterator = c.iterator()
        while (yIterator.hasNext()) {
            sb.append(VERTICAL_BORDER)
            val xIterator = yIterator.next().iterator()
            while (xIterator.hasNext()) {
                sb.append(xIterator.next())
            }
            sb.append("$VERTICAL_BORDER\n")
        }
        sb.append(horizontalBorder(c))
        return sb.toString()
    }

    private fun horizontalBorder(c: Canvas) = "${HORIZONTAL_BORDER * (c.width + 2)}\n"

    private operator fun Char.times(n: Int) = this.toString().repeat(n)
}