package dev.emg.testsample.thread

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import timber.log.Timber

class ThreadUtils() {

    private val _handlerThread = HandlerThread("ThreadUtils")

    init {
        _handlerThread.start()
    }

    fun getCallbackData(callback: TestCallback): MutableMap<String, String> {
        Timber.d("ThreadUtils: getCallbackData -> called")
        val map: MutableMap<String, String> = mutableMapOf()

        if (Looper.myLooper() == null)  {
            Timber.d("Before prepare: Looper.myLooper: ${Looper.myLooper()}")
            Looper.prepare()
            Timber.d("After prepare: Looper.myLooper: ${Looper.myLooper()}")
        }

        val backgroundHandlerThread = Handler(_handlerThread.looper)
        val handler = Handler(Looper.myLooper())

        backgroundHandlerThread.post {
            for (i in 1..5) {
                Timber.d("backgroundHandlerThread(): sleeping for $i...")
                map["map_entry_$i"] = "second_$i"
                Thread.sleep(1000)
            }
            notifyTestCallback(handler, callback, map)
        }
        Looper.loop()
        return map
    }

    private fun notifyTestCallback(
        handler: Handler,
        callback: TestCallback,
        map: MutableMap<String, String>
    ) {
        handler.post {
            callback.onReady(map)
        }
    }

}