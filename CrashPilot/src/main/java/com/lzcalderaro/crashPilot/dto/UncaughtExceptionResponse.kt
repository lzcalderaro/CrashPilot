package com.lzcalderaro.crashPilot.dto

data class UncaughtExceptionResponse(
    val device: Device = Device(),
    val application: Application = Application(),
    val throwable: Throwable = Throwable(),
    val timestamp: String = ""
)