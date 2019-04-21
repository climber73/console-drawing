package com.credit_suisse.drawing

import java.io.Closeable

interface Logger : Closeable {
    fun print(s: String)
    fun print(c: Char)
    fun println(s: String)
    fun println(c: Char)
    fun info(s: String?)
    fun error(s: String?)
}