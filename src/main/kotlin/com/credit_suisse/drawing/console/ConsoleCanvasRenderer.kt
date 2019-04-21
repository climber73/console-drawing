package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.Canvas
import com.credit_suisse.drawing.CanvasRenderer
import com.credit_suisse.drawing.Logger

const val HORIZONTAL_BORDER = '-'
const val VERTICAL_BORDER = '|'

class ConsoleCanvasRenderer(
    private val logger: Logger
) : CanvasRenderer {

    override fun close() {
    }

    override fun render(c: Canvas?) {
        if (c == null) return
        logger.println(horizontalBorder(c))
        //todo simplify:
        val yIterator = c.iterator()
        while (yIterator.hasNext()) {
            logger.print(VERTICAL_BORDER)
            val xIterator = yIterator.next().iterator()
            while (xIterator.hasNext()) {
                logger.print(xIterator.next())
            }
            logger.println(VERTICAL_BORDER)
        }
        logger.println(horizontalBorder(c))
    }

    private fun horizontalBorder(c: Canvas) = HORIZONTAL_BORDER * (c.width + 2)

    private operator fun Char.times(n: Int) = this.toString().repeat(n)
}