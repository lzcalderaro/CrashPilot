package com.lzcalderaro.crashPilot.src.controller

import com.lzcalderaro.crashPilot.api.Request
import com.lzcalderaro.crashPilot.dto.UncaughtExceptionResponse
import com.lzcalderaro.crashPilot.src.utils.Payload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CatchError: Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {

        runBlocking(Dispatchers.IO) {
            val response = UncaughtExceptionResponse(
                device = Payload.getDevice(),
                application = Payload.getApplication(e),
                throwable = Payload.getThrowable(e),
                timestamp = Payload.getCurrentDate()
            )

            Request.send(response)
        }
    }

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }
}