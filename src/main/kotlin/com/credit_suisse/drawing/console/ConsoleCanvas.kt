package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.*

const val MAX_WIDTH = 80
const val MAX_HEIGHT = 80

const val DEFAULT_COLOR = 'x'

class ConsoleCanvas(
    override val minX: Int,
    override val minY: Int,
    override val maxX: Int,
    override val maxY: Int
) : Canvas<Char, Int> {

    override val width: Int = maxX - minX + 1
    override val height: Int = maxY - minY + 1

    private val state: Array<Array<Char>> // array of rows (not columns), so Y is first coordinate

    init {
        require(width in 1..MAX_WIDTH) { "Canvas width should be in [1..$MAX_WIDTH]" }
        require(height in 1..MAX_HEIGHT) { "Canvas height should be in [1..$MAX_HEIGHT]" }
        state = Array(height) { Array(width) { BLANK_COLOR } }
    }

    override fun contain(p: Point<Char, Int>): Boolean {
        return p.x <= width && p.y <= height
    }

    override fun iterator(): Iterator<Iterable<Char>> {
        return object : Iterator<Iterable<Char>> {
            private var y = 0

            override fun hasNext(): Boolean {
                return y < height
            }

            override fun next(): Iterable<Char> {
                return state[y++].asIterable()
            }
        }
    }

    override fun add(shape: Shape<Char, Int>) {
        require(shape.isFit(this)) { "$shape doesn't fit canvas" }
        for (p in shape.points()) {
            state[p.y-1][p.x-1] = DEFAULT_COLOR
        }
    }

    override fun bucketFill(p: Point<Char, Int>) {
        require(contain(p)) { "Point $p doesn't fit canvas" }
        val x = p.x
        val y = p.y
        val original = state[y - 1][x - 1]
        bucketFill(x - 1, y - 1, original, p.attr)
    }

    private fun bucketFill(x: Int, y: Int, original: Char, c: Char) {
        state[y][x] = c
        if (y - 1 >= 0 && state[y - 1][x] == original)
            bucketFill(x, y - 1, original, c)
        if (y + 1 < height && state[y + 1][x] == original)
            bucketFill(x, y + 1, original, c)
        if (x - 1 >= 0 && state[y][x - 1] == original)
            bucketFill(x - 1, y, original, c)
        if (x + 1 < width && state[y][x + 1] == original)
            bucketFill(x + 1, y, original, c)
    }

    override fun close() {
        // nothing to do here
    }
}