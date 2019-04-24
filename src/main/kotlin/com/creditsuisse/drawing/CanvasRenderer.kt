package com.creditsuisse.drawing

/**
 *
 * A generic canvas renderer
 *
 * @param T the type in which to render canvas
 * @param C the type of pattern of points in the canvas
 *
 */
interface CanvasRenderer<T, C> {
    fun render(c: Canvas<C>?): T
}
