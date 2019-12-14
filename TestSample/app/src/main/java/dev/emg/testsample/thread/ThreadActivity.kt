package dev.emg.testsample.thread

import android.annotation.SuppressLint
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

    private val utils: ThreadUtils? = ThreadUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        if (utils == null) Timber.d("onCreate(): utils = null")

        callback_btn.setOnClickListener { getCallbackData() }
        main_thread_btn.setOnClickListener { observableMain() }
        background_thread_btn.setOnClickListener { observableBackground() }
    }


    private fun getCallbackData() {
        Timber.d("getCallbackData()")
        utils?.getCallbackData(object : TestCallback {
            override fun onReady(mutableMap: MutableMap<String, String>) {
                mutableMap.keys.forEach { key ->
                    Timber.d("Data: $key -> ${mutableMap[key]}")
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun observableMain() {
        Observable.fromCallable {
            getCallbackData()
            "observableMain():  Callable.call() -> nothing"
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), false, 100)
            .subscribe({}) { throwable -> Timber.e(throwable) }
    }

    @SuppressLint("CheckResult")
    private fun observableBackground() {
        Observable.fromCallable {
            getCallbackData()
            "observableBackground(): Callable.call -> Nothing"
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({}) { throwable -> Timber.e(throwable) }
    }

}
