package com.emgdev.daggerpractice.main

import android.os.Bundle
import android.widget.Toast
import com.emgdev.daggerpractice.BaseActivity
import com.emgdev.daggerpractice.R

/**
 * Created by emeruvia on 9/20/2019.
 */
class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Toast.makeText(this, "SOMETHING SOMETHIGN", Toast.LENGTH_SHORT).show()
  }
}