package com.credit_suisse.drawing

interface ShapeFactory<C, N : Number> {
    fun shape(cmd: AddShapeCommand): Shape<C, N>
}
