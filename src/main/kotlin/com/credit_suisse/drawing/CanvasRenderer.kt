package com.credit_suisse.drawing

import java.io.Closeable

interface CanvasRenderer : Closeable {
    fun render(c: Canvas?)
}
