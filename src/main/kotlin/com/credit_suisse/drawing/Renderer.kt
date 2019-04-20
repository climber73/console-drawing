package com.credit_suisse.drawing

import java.io.Closeable

interface Renderer : Closeable {
    fun render(c: Canvas?)
}
