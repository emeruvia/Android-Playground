package dev.emg.mvi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dev.emg.mvi.R

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    showMainFragment()
  }

  fun showMainFragment() {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, MainFragment(), "MainFragment")
        .commit()
  }

}
