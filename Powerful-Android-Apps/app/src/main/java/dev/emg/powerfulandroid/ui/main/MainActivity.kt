package dev.emg.powerfulandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emg.powerfulandroid.BaseApplication
import dev.emg.powerfulandroid.R
import dev.emg.powerfulandroid.di.main.MainComponent

//import dev.emg.powerfulandroid.ui.BaseActivity

//class MainActivity : BaseActivity() {
class MainActivity : AppCompatActivity() {

//  override fun inject() {
//    (application as BaseApplication).mainComponent()
//        .inject(this)
//  }

  lateinit var mainComponent: MainComponent

  override fun onCreate(savedInstanceState: Bundle?) {

    mainComponent = (application as BaseApplication).appComponent.mainComponent()
        .create()
    mainComponent.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
