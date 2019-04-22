package com.creditsuisse.drawing

/**
 *
 * A generic canvas renderer
 *
 * @param T the type which will be used to render in
 * @param P the type of points in the canvas
 * @param C the type of color of points in the canvas
 *
 */
interface CanvasRenderer<T, P : Point<C>, C> {
    fun render(c: Canvas<P, C>?): T
}
