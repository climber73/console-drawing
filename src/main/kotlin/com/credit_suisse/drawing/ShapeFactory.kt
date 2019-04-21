package com.credit_suisse.drawing

interface ShapeFactory<C> {
    fun shape(cmd: AddShapeCommand): Shape<C>
}
