package com.creditsuisse.drawing

import java.io.Closeable
import java.lang.Exception

interface Output : Closeable {
    fun print(s: String)
    fun println(s: String)
    fun printError(e: Exception)
}