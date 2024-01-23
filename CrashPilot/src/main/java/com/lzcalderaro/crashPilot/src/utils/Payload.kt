package com.lzcalderaro.crashPilot.src.utils

import android.os.Build
import com.lzcalderaro.crashPilot.api.Request
import com.lzcalderaro.crashPilot.dto.Application
import com.lzcalderaro.crashPilot.dto.Device
import com.lzcalderaro.crashPilot.dto.StackTrace
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object Payload {

    fun getCurrentDate(): String {
        DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
    }

    fun getApplication(e: Throwable): Application {
        return Application(
            className = e.javaClass.simpleName,
            packageName = Request.packageName()
        )
    }

    fun getThrowable(e: Throwable): com.lzcalderaro.crashPilot.dto.Throwable {

        val traces = mutableListOf<StackTrace>()

        e.stackTrace.forEach {
            traces.add(
                StackTrace(
                    className = it.className,
                    fileName = it.fileName,
                    methodName = it.methodName,
                    lineNumber = it.lineNumber
                )
            )
        }

        return com.lzcalderaro.crashPilot.dto.Throwable(
            cause = e.cause.toString(),
            message = e.message.toString(),
            traces = traces.toList()
        )
    }

    fun getDevice(): Device {
        return Device(
            serial = Build.SERIAL,
            model = Build.MODEL,
            id = Build.ID,
            manufacturer = Build.MANUFACTURER,
            brand = Build.BRAND,
            type = Build.TYPE,
            user = Build.USER,
            baseVersion = Build.VERSION_CODES.BASE,
            incrementalVersion = Build.VERSION.INCREMENTAL,
            sdk = Build.VERSION.SDK,
            board = Build.BOARD,
            host = Build.HOST,
            releaseVersion = Build.VERSION.RELEASE
        )
    }

}