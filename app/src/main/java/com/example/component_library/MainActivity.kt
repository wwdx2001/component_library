package com.example.component_library

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.customize_library.view.FrameGradientDrawable

class MainActivity : AppCompatActivity() {

  private var mView: View? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mView = findViewById<View>(R.id.view)

    val frameGradientDrawable = FrameGradientDrawable()
    frameGradientDrawable.setWidth(10f, 10f, 10f, 10f)
    frameGradientDrawable.setFrameColor(intArrayOf(Color.BLACK, Color.GREEN))
    frameGradientDrawable.setFramePositions(floatArrayOf(0.1f, 1f))
    frameGradientDrawable.setBackgroundColor(intArrayOf(Color.WHITE, Color.RED, Color.YELLOW))
    frameGradientDrawable.setBackgroundPositions(floatArrayOf(0.2f, 0.4f, 0.8f))
    frameGradientDrawable.setBackgroundDirection("toRightTop")
    frameGradientDrawable.setFrameRadius(10f)
    frameGradientDrawable.setBackgroundRadius(5f)
    mView?.background = frameGradientDrawable
  }
}
