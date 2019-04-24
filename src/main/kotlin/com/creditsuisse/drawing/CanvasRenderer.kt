package com.creditsuisse.drawing

/**
 *
 * A generic canvas renderer
 *
 * @param T the type which will be used to render in
 * @param C the type of pattern of points in the canvas
 *
 */
interface CanvasRenderer<T, C> {
    fun render(c: Canvas<C>?): T
}
