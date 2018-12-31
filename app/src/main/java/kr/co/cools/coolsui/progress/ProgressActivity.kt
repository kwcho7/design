package kr.co.cools.coolsui.progress

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_progress.*
import kr.co.cools.coolsui.R

class ProgressActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        circular.min = 0
        circular.max = 100
        circular.setStrokeColors(intArrayOf(Color.BLUE, Color.CYAN, Color.BLUE), floatArrayOf(0f, 0.5f, 1f))

        seekBar.max = circular.max

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                circular.setValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

}