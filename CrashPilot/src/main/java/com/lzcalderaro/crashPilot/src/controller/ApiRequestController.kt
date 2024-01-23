package com.lzcalderaro.crashPilot.src.controller

import android.webkit.URLUtil
import kotlinx.coroutines.coroutineScope
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.ConnectException
import java.net.MalformedURLException

class ApiRequestController(private val endpoint: String) {

    private val client = OkHttpClient()
    private val httpSuccessRange = 200..299

    init {
        validateUrl()
    }

    private fun validateUrl() {
       val isUrlValid = URLUtil.isValidUrl(endpoint)

        if (!isUrlValid) {
            throw MalformedURLException(endpoint)
        }
    }

    suspend fun connect(callback: (apiController: ApiRequestController) -> Unit) = coroutineScope {

        val testConnection = testConnection()

        if (!testConnection) {
            throw ConnectException("Unable to establish connection")
        }

        callback(this@ApiRequestController)
    }


    fun sendLog(json: String): Int {
        val body = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(endpoint)
            .post(body)
            .build()

        client.newCall(request).execute().use { response -> return response.code }
    }

    private fun testConnection(): Boolean {
        val request = Request.Builder()
            .url(endpoint)
            .build()

        client.newCall(request).execute().use { response -> return response.code in httpSuccessRange }
    }
}