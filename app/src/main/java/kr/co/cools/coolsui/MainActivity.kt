package kr.co.cools.coolsui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.cools.coolsui.header_deco.HeaderDecoActivity
import kr.co.cools.coolsui.progress.ProgressActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headerDeco.setOnClickListener {
            startActivity(Intent(this@MainActivity, HeaderDecoActivity::class.java))
        }

        progress.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProgressActivity::class.java))
        }
    }
}
