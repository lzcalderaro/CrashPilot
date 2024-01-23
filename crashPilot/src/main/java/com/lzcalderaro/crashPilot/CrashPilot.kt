package com.lzcalderaro.crashpilot

import android.app.Application
import com.lzcalderaro.crashpilot.api.Request
import com.lzcalderaro.crashpilot.src.controller.CatchError
import java.io.File

class CrashPilot(private val application: Application) {

    fun start(): CrashPilot {
        Request.setApplication(application)
        CatchError()
        return this
    }

    fun connectToServer(url: String): CrashPilot {
        Request.connect(url)
        return this
    }

    fun saveLogFile(file: File? = null): CrashPilot {
        Request.saveFile(file)
        return this
    }
}