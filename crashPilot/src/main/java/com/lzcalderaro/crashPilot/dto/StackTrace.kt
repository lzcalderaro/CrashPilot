package com.lzcalderaro.crashpilot.dto

data class StackTrace(
    val className: String = "",
    val fileName: String = "",
    val methodName: String = "",
    val lineNumber: Int = 0
)
