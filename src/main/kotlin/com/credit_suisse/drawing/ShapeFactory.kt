package com.credit_suisse.drawing

interface ShapeFactory<P : Point<C>, C> {
    fun shape(cmd: AddShapeCommand): Shape<P, C>
}
