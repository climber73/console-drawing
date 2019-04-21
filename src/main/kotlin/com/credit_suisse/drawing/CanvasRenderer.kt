package com.credit_suisse.drawing

/**
 *
 * A generic canvas renderer
 *
 * @param T the type which will be used to render in
 * @param C the type of main attribute of points contained in the canvas (color for example)
 * @param N the numeric type of coordinates in the canvas
 *
 */
interface CanvasRenderer<T, C, N : Number> {
    fun render(c: Canvas<C, N>?): T
}
