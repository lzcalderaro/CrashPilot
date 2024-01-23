package com.lzcalderaro.crashpilot.api

import android.app.Application
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.lzcalderaro.crashpilot.dto.Tag
import com.lzcalderaro.crashpilot.dto.UncaughtExceptionResponse
import com.lzcalderaro.crashpilot.src.controller.ApiRequestController
import com.lzcalderaro.crashpilot.src.controller.FileRequestController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.net.ConnectException
import java.net.MalformedURLException


object Request {

    private var application: Application? = null
    private var apiRequest: ApiRequestController? = null
    private var fileRequest: FileRequestController? = null

    fun connect(url: String) {

        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    ApiRequestController(url).connect {
                        apiRequest = it
                    }
                } catch (e: MalformedURLException) {
                    Log.e("CrashPilot", e.toString(), e)
                } catch (e: ConnectException) {
                    Log.e("CrashPilot", e.toString(), e)
                }
            }
        }
    }

    fun saveFile(file: File? = null) {

        runBlocking {
            launch {
                try {

                    val fileController = FileRequestController()

                    if (file != null) {
                        fileController.setCustomPath(file)
                    }

                    fileController.build {
                        fileRequest = it
                    }

                } catch (e: IOException) {
                    Log.e("CrashPilot", e.toString(), e)
                } catch (e: Exception) {
                    Log.e("CrashPilot", e.toString(), e)
                }
            }
        }

    }

    fun getLogFile() = fileRequest?.getFile()

    fun send(response: UncaughtExceptionResponse) {
        if (apiRequest == null && fileRequest == null) {
            return
        }

        val encode = ObjectMapper().writeValueAsString(response)
        apiRequest?.sendLog(encode)
        fileRequest?.write(encode)
    }

    fun send(response: Tag) {
        if (apiRequest == null && fileRequest == null) {
            return
        }

        val encode = ObjectMapper().writeValueAsString(response)
        apiRequest?.sendLog(encode)
        fileRequest?.write(encode)
    }

    fun packageName(): String {
        return application?.packageName.toString()
    }

    fun getApplication(): Application? {
        return application
    }

    fun setApplication(app: Application) {
        application = app
    }
}