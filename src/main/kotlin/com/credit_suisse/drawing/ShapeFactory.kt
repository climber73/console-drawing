package com.credit_suisse.drawing

interface ShapeFactory {
    fun shape(cmd: AddShapeCommand): Shape
}
