package com.lzcalderaro.crashpilot.dto

data class UncaughtExceptionResponse(
    val device: Device = Device(),
    val application: Application = Application(),
    val throwable: Throwable = Throwable(),
    val timestamp: String = ""
)