package com.lzcalderaro.crashPilot

import android.app.Application

class CrashPilot(private val application: Application) {

    fun start() {
        CatchError(application)
    }
}