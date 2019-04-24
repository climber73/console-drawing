package com.creditsuisse.drawing.console

import com.creditsuisse.drawing.*

class ConsoleApplication(
    private val renderer: ConsoleCanvasRenderer = ConsoleCanvasRenderer(),
    private val projectionFactory: ConsoleProjectionFactory = ConsoleProjectionFactory(),
    private val factory: ShapeFactory<Char> = ShapeFactory<Char>()
) : Application<String, Char> {

    private var canvas: Canvas<Char>? = null

    override fun createCanvas(cmd: CreateCanvas) {
        canvas = ConsoleCanvas(cmd.width, cmd.height)
    }

    override fun addLine(cmd: AddLine<Char>) {
        require(canvas != null) { "Canvas should be created before" }
        val line = factory.line(cmd)
        val points = projectionFactory.points(line)
        for (p in points) {
            canvas!!.set(p, cmd.c)
        }
    }

    override fun addRect(cmd: AddRect<Char>) {
        require(canvas != null) { "Canvas should be created before" }
        val rect = factory.rect(cmd)
        val points = projectionFactory.points(rect)
        for (p in points) {
            canvas!!.set(p, cmd.c)
        }
    }

    override fun bucketFill(cmd: BucketFill<Char>) {
        require(canvas != null) { "Canvas should be created before" }
        val p = Point(cmd.x, cmd.y)
        canvas!!.bucketFill(p, cmd.c)
    }

    override fun state(): String {
        return renderer.render(canvas)
    }
}