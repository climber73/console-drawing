package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.Canvas
import com.creditsuisse.drawing.Point

const val MAX_WIDTH = 80
const val MAX_HEIGHT = 20

const val BLANK_COLOR = ' '  // todo

class ConsoleCanvas(
    override val width: Int,
    override val height: Int,
    private val color: Char = BLANK_COLOR
) : Canvas<Char> {

    private val state: Array<Char> // rows of points go one by one

    init {
        require(width in 1..MAX_WIDTH) { "Canvas width should be in [1..$MAX_WIDTH]" }
        require(height in 1..MAX_HEIGHT) { "Canvas height should be in [1..$MAX_HEIGHT]" }
        state = Array(height * width) { color }
    }

    private fun contains(p: Point) =
        p.x in (1..width) && p.y in (1..height)

    override fun iterator() = state.iterator()

    override fun set(p: Point, c: Char) {
        require(this.contains(p)) { "$p doesn't fit canvas" }
        state[(p.y-1)*width + (p.x-1)] = c
    }

    override fun bucketFill(p: Point, c: Char) {
        require(contains(p)) { "Point $p doesn't fit canvas" }
        val x = p.x
        val y = p.y
        val original = state[(y - 1)*width + (x - 1)]
        bucketFill(x - 1, y - 1, original, c)
    }

    private fun bucketFill(x: Int, y: Int, original: Char, c: Char) {
        state[y*width + x] = c
        if (y - 1 >= 0 && state[(y - 1)*width + x] == original)
            bucketFill(x, y - 1, original, c)
        if (y + 1 < height && state[(y + 1)*width + x] == original)
            bucketFill(x, y + 1, original, c)
        if (x - 1 >= 0 && state[y*width + (x - 1)] == original)
            bucketFill(x - 1, y, original, c)
        if (x + 1 < width && state[y*width + (x + 1)] == original)
            bucketFill(x + 1, y, original, c)
    }

    override fun close() {
        // nothing to do here
    }
}