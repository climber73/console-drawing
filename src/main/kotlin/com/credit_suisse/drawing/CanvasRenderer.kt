package com.credit_suisse.drawing

import java.io.Closeable

interface CanvasRenderer<T> : Closeable {
    fun render(c: Canvas?): T
}
