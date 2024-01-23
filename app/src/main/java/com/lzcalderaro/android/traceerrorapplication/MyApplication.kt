package com.lzcalderaro.android.traceerrorapplication

import android.app.Application
import android.content.Context
import com.lzcalderaro.crashpilot.CrashPilot

class MyApplication: Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        CrashPilot(this).start().connectToServer("http://192.168.0.163:8080/log").saveLogFile()
    }
}