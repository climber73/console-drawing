package com.credit_suisse.drawing.console

import com.credit_suisse.drawing.*

const val MAX_WIDTH = 80
const val MAX_HEIGHT = 80


class ConsoleCanvas(
    override val width: Int,
    override val height: Int
) : Canvas<ConsolePoint, Char> {

    private val state: Array<Array<Char>> // array of rows (not columns), so Y is first coordinate

    init {
        require(width in 1..MAX_WIDTH) { "Canvas width should be in [1..$MAX_WIDTH]" }
        require(height in 1..MAX_HEIGHT) { "Canvas height should be in [1..$MAX_HEIGHT]" }
        state = Array(height) { Array(width) { BLANK_COLOR } }
    }

    override fun contains(p: ConsolePoint): Boolean {
        return p.x in (1..width) && p.y in (1..height)
    }

    // todo : test!
    override fun contains(shape: ConsoleShape): Boolean {
        return shape.points().all { contains(it) }
    }

    override fun iterator(): Iterator<Char> {
        return object : Iterator<Char> {
            private var y = 0
            private var x = 0

            override fun hasNext(): Boolean {
                return y < height && x < width
            }

            override fun next(): Char {
                val next = state[y][x++]
                if (x >= width) {
                    x = 0
                    y++
                }
                return next
            }
        }
    }

    override fun add(shape: ConsoleShape) {
        require(this.contains(shape)) { "$shape doesn't fit canvas" }
        for (p in shape.points()) {
            state[p.y-1][p.x-1] = DEFAULT_COLOR
        }
    }

    override fun bucketFill(p: ConsolePoint, c: Char) {
        require(contains(p)) { "Point $p doesn't fit canvas" }
        val x = p.x
        val y = p.y
        val original = state[y - 1][x - 1]
        bucketFill(x - 1, y - 1, original, c)
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