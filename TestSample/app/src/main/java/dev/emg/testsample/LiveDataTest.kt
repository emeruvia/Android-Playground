package dev.emg.testsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LiveDataTest {

    private val _liveData1 = MutableLiveData<List<String>>()
    val liveData1: LiveData<List<String>>
        get() = _liveData1

    private val _liveData2 = MutableLiveData<List<String>>()
    val liveData2: LiveData<List<String>>
        get() = _liveData2

    private val _liveData3 = MutableLiveData<List<String>>()
    val liveData3: LiveData<List<String>>
        get() = _liveData3

    private val _liveData4 = MutableLiveData<List<String>>()
    val liveData4: LiveData<List<String>>
        get() = _liveData4

//    fun setup() {
//        _liveData1.postValue(listOf("A", "B", "C"))
//        _liveData2.postValue(listOf("D", "E"))
//        _liveData3.postValue(listOf("F"))
//        _liveData4.postValue(listOf("G", "H", "I", "J"))
//    }
    fun setup() {
        _liveData1.value = listOf("A", "B", "C")
        _liveData2.value = listOf("D", "E")
        _liveData3.value = listOf("F")
        _liveData4.value = listOf("G", "H", "I", "J")
    }
}