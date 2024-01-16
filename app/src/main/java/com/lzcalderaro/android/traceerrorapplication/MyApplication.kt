package com.lzcalderaro.android.traceerrorapplication

import android.app.Application
import android.content.Context
import android.util.Log
import com.lzcalderaro.crashPilot.CrashPilot

class MyApplication: Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        Log.d("HEYYYY", "CA ESTOU NOVAMENTE")
        CrashPilot(this).start()
    }
}