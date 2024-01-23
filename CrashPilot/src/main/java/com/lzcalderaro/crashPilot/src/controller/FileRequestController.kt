package com.lzcalderaro.crashPilot.src.controller

import com.lzcalderaro.crashPilot.api.Request
import kotlinx.coroutines.coroutineScope
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileRequestController {

    private val fileName: String = "crashPilot.log"
    private var logFile: File? = null

    suspend fun build(callback: (fileRequestController: FileRequestController) -> Unit) = coroutineScope {

        if (logFile == null) {
            setDefaultPath()
        }

        isWritten()

        callback(this@FileRequestController)
    }

    fun setCustomPath(file: File): Any {
        logFile = file
        return this
    }

    private fun isWritten() {

        logFile.let {
            if (it?.isFile == false) throw IOException("No file was defined")
        }

        if (logFile?.isFile == false) {
            throw IOException("No file was defined")
        }

        if (logFile?.exists() == false) {
            write("First Contet")
        }
    }

    fun write(content: String) {
        val outputStream = FileOutputStream(logFile)
        outputStream.write(content.toByteArray())
        outputStream.write("\n".toByteArray())
        outputStream.close()
    }

    fun getFile(): File? {
        return logFile
    }

    private fun setDefaultPath() {
        val context = Request.getApplication()
        val dir = context?.filesDir
        logFile = File(dir, fileName)
    }

}