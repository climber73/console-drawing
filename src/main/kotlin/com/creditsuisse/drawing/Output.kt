package com.creditsuisse.drawing

import java.io.Closeable

interface Output : Closeable {
    fun print(s: String)
    fun println(s: String)
    fun printError(s: String?)
}