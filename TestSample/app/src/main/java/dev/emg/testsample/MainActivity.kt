package dev.emg.testsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.emg.testsample.thread.ThreadActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread_btn.setOnClickListener {
            val intent = Intent(this, ThreadActivity::class.java)
            startActivity(intent)
        }
    }
}
