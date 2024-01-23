package com.lzcalderaro.crashPilot.dto

data class Throwable(
    val cause: String = "",
    val message: String = "",
    val traces: List<StackTrace> = arrayListOf()
)