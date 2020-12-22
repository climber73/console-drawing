package com.creditsuisse.drawing

/**
 *  Canvas backed by array of elements of C type
 */
abstract class AbstractCanvas<C>(
    final override val width: Int,
    final override val height: Int,
    private val converter: ShapeConverter,
    private val state: Array<C>  // rows of points go one by one
) : Canvas<C> {

    init {
        require(width in 1..MAX_WIDTH) { "Canvas width should be in [1..$MAX_WIDTH]" }
        require(height in 1..MAX_HEIGHT) { "Canvas height should be in [1..$MAX_HEIGHT]" }
    }

    override fun iterator() = state.iterator()

    override fun set(p: Point, c: C) {
        require(this.contains(p)) { "$p doesn't fit canvas" }
        state[(p.y-1)*width + (p.x-1)] = c
    }

    override fun draw(shape: Shape, c: C) {
        require(contains(shape)) { "Can't place $shape into $this" }
        val points = converter.convert(shape)
        for (p in points) {
            set(p, c)
        }
    }

    override fun bucketFill(p: Point, c: C) {
        require(contains(p)) { "$p doesn't fit canvas" }
        val x = p.x
        val y = p.y
        val original = state[(y - 1)*width + (x - 1)]
        bucketFill(x - 1, y - 1, original, c)
    }

    private fun contains(p: Point) =
        p.x in (1..width) && p.y in (1..height)

    private fun contains(shape: Shape): Boolean {
        return 0 < shape.xMin() && shape.xMax() <= width && 0 < shape.yMin() && shape.yMax() <= height
    }

    private fun bucketFill(x: Int, y: Int, original: C, c: C) {
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

    }

    override fun toString(): String {
        return "Canvas ${width}x$height"
    }

    companion object {
        private const val MAX_WIDTH = 80
        private const val MAX_HEIGHT = 20
    }
}
