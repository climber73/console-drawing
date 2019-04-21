package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.*

const val MAX_WIDTH = 80
const val MAX_HEIGHT = 80

const val DEFAULT_COLOR = 'x'
const val BLANK_COLOR = ' '

class ConsoleCanvas(
    override val width: Int,
    override val height: Int
) : Canvas<Char> {

    private val state: Array<Array<Char>> // array of rows (not columns), so Y is first coordinate

    init {
        require(width in 1..MAX_WIDTH) { "Canvas width should be in [1..$MAX_WIDTH]" }
        require(height in 1..MAX_HEIGHT) { "Canvas height should be in [1..$MAX_HEIGHT]" }
        state = Array(height) { Array(width) { BLANK_COLOR } }
    }

    override fun contain(p: Point): Boolean {
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

    override fun add(shape: Shape) {
        require(shape.isFit(this)) { "$shape doesn't fit canvas" }
        require(shape is ConsoleShape) { "$shape isn't supported in console canvas" }
        for (p in shape.points()) {
            state[p.y-1][p.x-1] = DEFAULT_COLOR
        }
    }

    override fun bucketFill(fill: BucketFill) {
        val x = fill.x
        val y = fill.y
        require(contain(ConsolePoint(x, y))) { "Point x=$x y=$y doesn't fit canvas" }
        bucketFill(x - 1, y - 1, state[y - 1][x - 1], fill.c)
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