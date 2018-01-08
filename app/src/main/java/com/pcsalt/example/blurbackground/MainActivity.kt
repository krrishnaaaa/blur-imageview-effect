package com.pcsalt.example.blurbackground

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import com.pcsalt.example.blurbackground.Blur.fastblur


class MainActivity : AppCompatActivity() {

    private lateinit var bgImage: ImageView
    private lateinit var seekBarBlur: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bgImage = findViewById(R.id.imageView)
        seekBarBlur = findViewById(R.id.seekBar)

        val bg = BitmapFactory.decodeResource(resources, R.drawable.dog)
        val blurred = fastblur(bg, 1f, 10)
        bgImage.setImageBitmap(blurred)

        initBlurEffect()
    }

    private fun initBlurEffect() {

        seekBarBlur.setMax(100)

        seekBarBlur.setKeyProgressIncrement(1)
        seekBarBlur.setProgress(0)
        seekBarBlur.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                bgImage.setImageResource(R.drawable.dog)
                bgImage.setDrawingCacheEnabled(true)
                val bitmap = bgImage.getDrawingCache()
                if (i == 0) {
                    bgImage.setImageResource(R.drawable.dog)
                } else {
                    Log.e("seek......", i.toString() + "")

                    val blurred = fastblur(bitmap, 1f, i)
                    bgImage.setImageBitmap(blurred)
                    bgImage.invalidate()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }
}
