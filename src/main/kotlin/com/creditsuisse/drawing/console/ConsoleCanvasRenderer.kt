package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Canvas
import com.creditsuisse.drawing.CanvasRenderer
import java.lang.StringBuilder

const val HORIZONTAL_BORDER = '-'
const val VERTICAL_BORDER = '|'

class ConsoleCanvasRenderer : CanvasRenderer<String, ConsolePoint, Char> {

    override fun render(c: Canvas<ConsolePoint, Char>?): String {
        if (c == null) return ""
        val sb = StringBuilder()
        sb.append(horizontalBorder(c))

        c.forEachIndexed { i, color ->

            if (i % c.width == 0) {
                sb.append(VERTICAL_BORDER)
            }

            sb.append(color)

            if ((i + 1) % c.width == 0) {
                sb.append("$VERTICAL_BORDER\n")
            }

        }
        sb.append(horizontalBorder(c))
        return sb.toString()
    }

    private fun horizontalBorder(c: Canvas<ConsolePoint, Char>) = "${HORIZONTAL_BORDER * (c.width + 2)}\n"

    private operator fun Char.times(n: Int) = this.toString().repeat(n)
}