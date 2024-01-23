package com.lzcalderaro.crashPilot.src.utils

import android.app.Application
import com.lzcalderaro.crashPilot.api.Request

fun Application.getCpLogFile()  = Request.getLogFile()
