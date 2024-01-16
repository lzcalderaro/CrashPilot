package com.lzcalderaro.crashPilot

import android.app.Application
import android.os.Build
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class CatchError(private val application: Application): Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {

        val message = "you got caught a ${e.javaClass.simpleName} for ${application.packageName}"

        Log.d("HEYYYY", message)
        Log.d("HEYYYY", e.message.toString())
        Log.d("HEYYYY", e.cause.toString())


        Log.d("HEYYYY", "SERIAL: " + Build.SERIAL);
        Log.d("HEYYYY","MODEL: " + Build.MODEL);
        Log.d("HEYYYY","ID: " + Build.ID);
        Log.d("HEYYYY","Manufacture: " + Build.MANUFACTURER);
        Log.d("HEYYYY","brand: " + Build.BRAND);
        Log.d("HEYYYY","type: " + Build.TYPE);
        Log.d("HEYYYY","user: " + Build.USER);
        Log.d("HEYYYY","BASE: " + Build.VERSION_CODES.BASE);
        Log.d("HEYYYY","INCREMENTAL " + Build.VERSION.INCREMENTAL);
        Log.d("HEYYYY","SDK  " + Build.VERSION.SDK);
        Log.d("HEYYYY","BOARD: " + Build.BOARD);
        Log.d("HEYYYY","BRAND " + Build.BRAND);
        Log.d("HEYYYY","HOST " + Build.HOST);
        Log.d("HEYYYY","FINGERPRINT: "+Build.FINGERPRINT);
        Log.d("HEYYYY","Version Code: " + Build.VERSION.RELEASE);

        for (traces in e.stackTrace) {
            Log.d("HEYYYY", traces.className)
        }

        val path = application.filesDir
        val file = File(path, "Records.txt")

        FileOutputStream(file).use {
            it.write(message.toByteArray())
        }
    }

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }
}