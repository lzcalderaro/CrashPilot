package com.lzcalderaro.crashpilot.dto


data class Tag(
    val type: String = "",
    val tag: String = "",
    val message: String = "",
    val throwable: Throwable? = null,
    val timestamp: String = "",
    val device: Device = Device(),
    val application: Application? = null
)