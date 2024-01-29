package dev.subfly.kmpandroidcmpktor.util.measure

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Choreographer
import java.io.File
import java.io.FileWriter

class FpsMeasurer(
    private val context: Context
) {
    private var frameCount = 0
    private var stepCount = 0
    private var startTimeMillis: Long = 0
    private val fpsValues = mutableMapOf<Int, Long>()

    private val callback by lazy {
        object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                frameCount++

                val currentTimeMillis = System.currentTimeMillis()
                val elapsedTimeMillis = currentTimeMillis - startTimeMillis

                if (elapsedTimeMillis >= 1000) {
                    val fps = (frameCount * 1000) / elapsedTimeMillis
                    stepCount++

                    fpsValues[stepCount] = fps

                    frameCount = 0
                    startTimeMillis = currentTimeMillis
                }

                Choreographer.getInstance().postFrameCallback(this)
            }
        }
    }

    fun startCapturingFrames(durationMillis: Long) {
        startTimeMillis = System.currentTimeMillis()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            stopCapturing()
        }, durationMillis)

        startFrameCallback()
    }

    private fun startFrameCallback() {
        Choreographer.getInstance().postFrameCallback(callback)
    }

    private fun stopCapturing() {
        Choreographer.getInstance().removeFrameCallback(callback)
        writeFpsToFile()
    }

    private fun writeFpsToFile() {
        val file = File(context.cacheDir, "fps_values.csv")
        val writer = FileWriter(file)

        // Write each FPS value to the file
        fpsValues.forEach { (key, value) ->
            writer.write("$key,$value\n")
        }

        // Close the FileWriter
        writer.close()
        FileUploader.uploadFile(file)
    }
}
