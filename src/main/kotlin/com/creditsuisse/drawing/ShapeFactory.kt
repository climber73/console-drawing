package com.creditsuisse.drawing

interface ShapeFactory<P : Point<C>, C> {
    fun shape(cmd: AddShapeCommand): Shape<P, C>
}
