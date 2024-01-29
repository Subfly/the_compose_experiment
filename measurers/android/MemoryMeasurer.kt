package dev.subfly.kmpandroidcmpktor.util.measure

import android.content.Context
import android.os.Debug
import android.os.Handler
import android.os.Looper
import java.io.File
import java.io.FileWriter

class MemoryMeasurer(private val context: Context) {
    private val memoryUsageValues = mutableMapOf<Int, Double>()
    private var step = 0
    private var handler: Handler? = null

    fun startMeasuringMemoryUsage(duration: Long) {
        handler = Handler(Looper.getMainLooper())
        handler?.postDelayed(object : Runnable {
            override fun run() {
                measureMemoryUsage()
                handler?.postDelayed(this, 1000)
            }
        }, 0)

        Handler(Looper.getMainLooper()).postDelayed({
            stopMeasuring()
        }, duration)
    }

    private fun measureMemoryUsage() {
        val memoryInfo = Debug.MemoryInfo()
        Debug.getMemoryInfo(memoryInfo)
        val usedPssMb = memoryInfo.totalPss / 1024.0
        step++
        memoryUsageValues[step] = usedPssMb
    }

    private fun stopMeasuring() {
        handler?.removeCallbacksAndMessages(null)
        printMemoryUsageValues()
    }

    private fun printMemoryUsageValues() {
        val file = File(context.cacheDir, "memory_values.csv")
        val writer = FileWriter(file)

        // Write each FPS value to the file
        memoryUsageValues.forEach { (key, value) ->
            writer.write("$key,$value\n")
        }

        // Close the FileWriter
        writer.close()
        FileUploader.uploadFile(file)
    }
}
