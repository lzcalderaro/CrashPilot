package com.lzcalderaro.crashpilot.src.utils

import android.app.Application
import com.lzcalderaro.crashpilot.api.Request

fun Application.getCpLogFile()  = Request.getLogFile()
