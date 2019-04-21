package com.credit_suisse.drawing

import java.io.Closeable

/**
 *
 * A generic canvas renderer
 *
 * @param T the type which will be used to render in
 * @param C the type of elements contained in the canvas
 *
 */
interface CanvasRenderer<T, C> : Closeable {
    fun render(c: Canvas<C>?): T
}
