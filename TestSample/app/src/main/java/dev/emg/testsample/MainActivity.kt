package dev.emg.testsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import dev.emg.testsample.thread.ThreadActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread_btn.setOnClickListener {
            val intent = Intent(this, ThreadActivity::class.java)
            startActivity(intent)
        }

        livedata_btn.setOnClickListener {
            liveDataTest()
        }
    }

    private fun liveDataSample() {
        val first = MutableLiveData<Int>()
        first.value = 1
        val sec = MutableLiveData<Int>()
        sec.value = 2
        val third = MutableLiveData<Int>()
        third.value = 3
        val fourth = MutableLiveData<Int>()
        fourth.value = 4

        val merged = MediatorLiveData<Int>()
        merged.addSource(first) {

        }
    }

    private fun liveDataTest() {
        val sample = LiveDataTest()
        sample.setup()

        val liveDataMerger = MediatorLiveData<List<String>>()

        liveDataMerger.addSource(sample.liveData1) { value ->
            liveDataMerger.value = combineLiveDataList(sample.liveData1, sample.liveData2)
        }
        liveDataMerger.addSource(sample.liveData2) { value ->
            liveDataMerger.value = combineLiveDataList(sample.liveData1, sample.liveData2)
        }

        Timber.d("liveDataTest(): liveDataMerger -> ${liveDataMerger.value}")
    }

    private fun combineLiveDataList(
        liveData1: LiveData<List<String>>,
        liveData2: LiveData<List<String>>
    ): List<String> {
        val first = liveData1.value
        val second = liveData2.value

        val combined = mutableListOf<String>()
        for (i in first!!) {
            combined.add(i)
        }
        for (i in second!!) {
            combined.add(i)
        }

        return combined
    }
}
