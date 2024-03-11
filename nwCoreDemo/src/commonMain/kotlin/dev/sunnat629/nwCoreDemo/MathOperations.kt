package dev.sunnat629.nwCoreDemo

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

object MathOperations {
    fun add(a: Int, b: Int): Int = a + b
    fun sub(a: Int, b: Int): Int = a - b
}

@OptIn(ExperimentalJsExport::class)
@JsExport
class MathOp {
    fun add(a: Int, b: Int): Int = a + b
    fun sub(a: Int, b: Int): Int = a - b
}