package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Canvas
import com.credit_suisse.drawing.CanvasRenderer
import java.lang.StringBuilder

const val HORIZONTAL_BORDER = '-'
const val VERTICAL_BORDER = '|'

class ConsoleCanvasRenderer : CanvasRenderer<String, ConsolePoint, Char> {

    override fun render(c: Canvas<ConsolePoint, Char>?): String {
        if (c == null) return ""
        val sb = StringBuilder()
        sb.append(horizontalBorder(c))
        c.forEach { raw ->
            sb.append(VERTICAL_BORDER)
            raw.forEach { sb.append(it.color) }
            sb.append("$VERTICAL_BORDER\n")
        }
//        val yIterator = c.iterator()
//        while (yIterator.hasNext()) {
//            sb.append(VERTICAL_BORDER)
//            val xIterator = yIterator.next().iterator()
//            while (xIterator.hasNext()) {
//                sb.append(xIterator.next())
//            }
//            sb.append("$VERTICAL_BORDER\n")
//        }
        sb.append(horizontalBorder(c))
        return sb.toString()
    }

    private fun horizontalBorder(c: Canvas<ConsolePoint, Char>) = "${HORIZONTAL_BORDER * (c.width + 2)}\n"

    private operator fun Char.times(n: Int) = this.toString().repeat(n)
}