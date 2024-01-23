package com.lzcalderaro.crashpilot.src.controller

import android.util.Log
import com.lzcalderaro.crashpilot.api.Request
import com.lzcalderaro.crashpilot.dto.Application
import com.lzcalderaro.crashpilot.dto.Tag
import com.lzcalderaro.crashpilot.list.ErrorTypes
import com.lzcalderaro.crashpilot.src.utils.Payload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import com.lzcalderaro.crashpilot.dto.Throwable as ThrowablePayload

object CpLog {

    fun e(tag: String, msg: String, tr:Throwable? = null) {
        sendRequest(ErrorTypes.ERROR,tag, msg, tr)
    }

    fun d(tag: String, msg: String, tr:Throwable? = null) {
        sendRequest(ErrorTypes.DEBUG,tag, msg, tr)
    }

    fun v(tag: String, msg: String, tr:Throwable? = null) {
        sendRequest(ErrorTypes.VERBOSE,tag, msg, tr)
    }

    fun i(tag: String, msg: String, tr:Throwable? = null) {
        sendRequest(ErrorTypes.INFO,tag, msg, tr)
    }

    fun w(tag: String, msg: String, tr:Throwable? = null) {
        sendRequest(ErrorTypes.WARNING,tag, msg, tr)
    }

    private fun sendRequest(type:ErrorTypes, tag: String, msg: String, tr:Throwable?) {

        when(type) {
            ErrorTypes.ERROR -> Log.e(tag, msg, tr)
            ErrorTypes.DEBUG -> Log.d(tag, msg, tr)
            ErrorTypes.VERBOSE -> Log.v(tag, msg, tr)
            ErrorTypes.INFO -> Log.i(tag, msg, tr)
            ErrorTypes.WARNING -> Log.w(tag, msg, tr)
        }

        runBlocking(Dispatchers.IO) {
            var trPayload: ThrowablePayload? = null
            var applicationPayload: Application? = null

            if (tr != null) {
                trPayload = Payload.getThrowable(tr)
                applicationPayload = Payload.getApplication(tr)
            }

            val payload = Tag(
                type = type.name,
                tag = tag,
                message = msg,
                throwable = trPayload,
                timestamp = Payload.getCurrentDate(),
                device = Payload.getDevice(),
                application = applicationPayload
            )

            Request.send(payload)
        }
    }
}