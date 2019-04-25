package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*

const val BLANK_COLOR = ' '

class ConsoleApplication(
    private val renderer: ConsoleCanvasRenderer = ConsoleCanvasRenderer()
) : Application<String, Char> {

    private var canvas: Canvas<Char>? = null

    override fun createCanvas(width: Int, height: Int) {
        canvas = DefaultCanvas<Char>(
            width = width,
            height = height,
            state = Array<Char>(height * width) { BLANK_COLOR },
            converter = ConsoleShapeConverter()
        )
    }

    override fun addLine(line: Line, c: Char) {
        require(canvas != null) { "Canvas should be created before" }
        canvas!!.draw(line, c)
    }

    override fun addRect(rect: Rect, c: Char) {
        require(canvas != null) { "Canvas should be created before" }
        canvas!!.draw(rect, c)
    }

    override fun bucketFill(p: Point, c: Char) {
        require(canvas != null) { "Canvas should be created before" }
        canvas!!.bucketFill(p, c)
    }

    override fun state(): String {
        return renderer.render(canvas)
    }
}