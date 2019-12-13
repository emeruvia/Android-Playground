package dev.emg.testsample.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import dev.emg.testsample.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_thread.*
import timber.log.Timber
import java.util.*
import java.util.concurrent.Callable

class ThreadActivity : AppCompatActivity() {

    private val thread = HandlerThread("TestThread")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        Timber.d("onCreate()")

        thread.start()

        main_thread_btn.setOnClickListener { observableMain() }
        background_thread_btn.setOnClickListener { observableBackground() }
    }

    private fun observableMain() {
        Observable.fromCallable(object : Callable<String> {
            override fun call(): String {
                getMsgThroughHandler(object: TestCallback {
                    override fun onReady(msg: String) {
                        Timber.d("observable: fromCallable: call: onReady(): $msg")
                    }

                })
                return "observable(): call retrun"
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}) {throwable -> Timber.e(throwable)}
    }

    private fun observableBackground() {
        Observable.fromCallable(object : Callable<String> {
            override fun call(): String {
                getMsgThroughHandler(object: TestCallback {
                    override fun onReady(msg: String) {
                        Timber.d("observable: fromCallable: call: onReady(): $msg")
                    }

                })
                return "observable(): call retrun"
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({}) {throwable -> Timber.e(throwable)}
    }

    private fun getMsgThroughHandler(testCallback: TestCallback): String {
        Looper.prepare()
        val backgroundHandler = Handler(thread.looper);
        val handler = Handler(Looper.myLooper())
        var msg = "before handler"
        backgroundHandler.post{
            for(i in 0..5) {
                Timber.d("Sleep for $i seconds")
                Thread.sleep(1000)
            }
            msg = "inside handler"
            notifyCallback(handler, testCallback, msg)
        }
        Looper.loop()
        return msg
    }

    private fun notifyCallback(handler: Handler, testCallback: TestCallback, msg: String) {
        handler.post { testCallback.onReady(msg) }
    }

}
