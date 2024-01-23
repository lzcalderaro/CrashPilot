package com.lzcalderaro.crashPilot.dto

data class StackTrace(
    val className: String = "",
    val fileName: String = "",
    val methodName: String = "",
    val lineNumber: Int = 0
)
