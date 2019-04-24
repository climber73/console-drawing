package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Canvas
import com.creditsuisse.drawing.CanvasRenderer

const val HB = '-'
const val VB = '|'

class ConsoleCanvasRenderer : CanvasRenderer<String, Char> {

    override fun render(c: Canvas<Char>?): String {
        if (c == null) return ""
        return divider(c) + body(c) + divider(c)
    }

    private fun body(c: Canvas<Char>) =
        c.chunked(c.width).joinToString("") { raw ->
            "$VB${raw.joinToString("")}$VB\n"
        }

    private fun divider(c: Canvas<Char>) = "${HB * (c.width + 2)}\n"

    private operator fun Char.times(n: Int) = this.toString().repeat(n)
}