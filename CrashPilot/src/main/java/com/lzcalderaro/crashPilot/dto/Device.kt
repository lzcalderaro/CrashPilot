package com.lzcalderaro.crashPilot.dto

data class Device(
    val serial: String = "",
    val model: String = "",
    val manufacturer: String = "",
    val id: String = "",
    val brand: String = "",
    val type: String = "",
    val user: String = "",
    val baseVersion: Int = 0,
    val incrementalVersion: String = "",
    val sdk: String = "",
    val board: String = "",
    val host: String = "",
    val releaseVersion: String = ""
)
