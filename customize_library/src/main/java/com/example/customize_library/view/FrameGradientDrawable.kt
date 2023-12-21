package com.example.customize_library.view

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.TextUtils

class FrameGradientDrawable(
) : GradientDrawable() {

  private var mLeftFrameWidth = 0f
  private var mTopFrameWidth = 0f
  private var mRightFrameWidth = 0f
  private var mBottomFrameWidth = 0f

  private var mFrameRadius = 0f
  private var mFrameRadii: FloatArray = floatArrayOf()
  private var mBackgroundRadius = 0f
  private var mBackgroundRadii: FloatArray = floatArrayOf()

  private var mWidth = 0f
  private var mHeight = 0f

  private var mDrawableWidth = 0
  private var mDrawableHeight = 0

  private val mOutPath = Path()
  private val mInnerPath = Path()
  private val mBackgroundPath = Path()

  private val mFramePaint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val mBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)

  private var mFrameShader: Shader? = null
  private var mBackgroundShader: Shader? = null

  private var mFrameColor = Color.WHITE
  private var mFrameColors: IntArray = intArrayOf()
  private var mBackgroundColor = Color.WHITE
  private var mBackgroundColors: IntArray = intArrayOf()

  private var mFramePositions: FloatArray = floatArrayOf()
  private var mBackgroundPositions: FloatArray = floatArrayOf()

  private var mFrameDirection = ""
  private var mBackgroundDirection = ""

  private var mRectF: RectF? = null


  init {
  }

  fun setWidth(
    leftFrameWidth: Float,
    topFrameWidth: Float,
    rightFrameWidth: Float,
    bottomFrameWidth: Float
  ) {
    mLeftFrameWidth = leftFrameWidth
    mTopFrameWidth = topFrameWidth
    mRightFrameWidth = rightFrameWidth
    mBottomFrameWidth = bottomFrameWidth
  }

  fun setFrameRadius(radius: Float) {
    mFrameRadius = radius
  }

  fun setFrameRadius(radii: FloatArray) {
    mFrameRadii = radii
  }

  fun setBackgroundRadius(radius: Float) {
    mBackgroundRadius = radius
  }

  fun setBackgroundRadius(radii: FloatArray) {
    mBackgroundRadii = radii
  }

  fun setFrameColor(color: Int) {
    mFrameColor = color
  }

  fun setFrameColor(colors: IntArray) {
    mFrameColors = colors
  }

  fun setBackgroundColor(color: Int) {
    mBackgroundColor = color
  }

  fun setBackgroundColor(colors: IntArray) {
    mBackgroundColors = colors
  }

  fun setFramePositions(positions: FloatArray) {
    mFramePositions = positions
  }

  fun setBackgroundPositions(positions: FloatArray) {
    mBackgroundPositions = positions
  }

  fun setFrameDirection(direction: String) {
    mFrameDirection = direction
  }

  fun setBackgroundDirection(direction: String) {
    mBackgroundDirection = direction
  }

  @SuppressLint("ObsoleteSdkInt")
  override fun draw(canvas: Canvas) {

    val width = bounds.width().toFloat()
    val height = bounds.height().toFloat()
    if (mWidth == 0f || mHeight == 0f || mWidth != width || mHeight != height) {
      mOutPath.reset()
      mInnerPath.reset()
      mBackgroundPath.reset()
      if (Build.VERSION.SDK_INT >= 21) {
        if (mFrameRadii.isNotEmpty()) {
          mOutPath.addRoundRect(
            0f,
            0f,
            width,
            height,
            mFrameRadii,
            Path.Direction.CW
          )
          mInnerPath.addRoundRect(
            mLeftFrameWidth,
            mTopFrameWidth,
            width - mRightFrameWidth,
            height - mBottomFrameWidth,
            mFrameRadii,
            Path.Direction.CW
          )
          mBackgroundPath.addRoundRect(
            mLeftFrameWidth,
            mTopFrameWidth,
            width - mRightFrameWidth,
            height - mBottomFrameWidth,
            mFrameRadii,
            Path.Direction.CW
          )
        } else {
          mOutPath.addRoundRect(
            0f,
            0f,
            width,
            height,
            mFrameRadius,
            mFrameRadius,
            Path.Direction.CW
          )
          mInnerPath.addRoundRect(
            mLeftFrameWidth,
            mTopFrameWidth,
            width - mRightFrameWidth,
            height - mBottomFrameWidth,
            mFrameRadius,
            mFrameRadius,
            Path.Direction.CW
          )
          mBackgroundPath.addRoundRect(
            mLeftFrameWidth,
            mTopFrameWidth,
            width - mRightFrameWidth,
            height - mBottomFrameWidth,
            mFrameRadius,
            mFrameRadius,
            Path.Direction.CW
          )
        }
      } else {
        if (mFrameRadii.isNotEmpty()) {
          mOutPath.addRoundRect(
            RectF(0f, 0f, width, height),
            mFrameRadii,
            Path.Direction.CW
          )
          mInnerPath.addRoundRect(
            RectF(
              mLeftFrameWidth,
              mTopFrameWidth,
              width - mRightFrameWidth,
              height - mBottomFrameWidth
            ), mFrameRadii, Path.Direction.CW
          )
          mBackgroundPath.addRoundRect(
            RectF(
              mLeftFrameWidth,
              mTopFrameWidth,
              width - mRightFrameWidth,
              height - mBottomFrameWidth
            ), mFrameRadii, Path.Direction.CW
          )
        } else {
          mOutPath.addRoundRect(
            RectF(0f, 0f, width, height),
            mFrameRadius,
            mFrameRadius,
            Path.Direction.CW
          )
          mInnerPath.addRoundRect(
            RectF(
              mLeftFrameWidth,
              mTopFrameWidth,
              width - mRightFrameWidth,
              height - mBottomFrameWidth
            ), mFrameRadius, mFrameRadius, Path.Direction.CW
          )
          mBackgroundPath.addRoundRect(
            RectF(
              mLeftFrameWidth,
              mTopFrameWidth,
              width - mRightFrameWidth,
              height - mBottomFrameWidth
            ), mFrameRadius, mFrameRadius, Path.Direction.CW
          )
        }
      }

      if (mFrameColors.isNotEmpty()) {
        mFrameShader =
          getLinearGradient(width, height, mFrameColors, mFramePositions, mFrameDirection)
        mFramePaint.shader = mFrameShader
      } else {
        mFramePaint.color = mFrameColor
      }

      if (mBackgroundColors.isNotEmpty()) {
        mBackgroundShader = getLinearGradient(
          width - mRightFrameWidth, height - mTopFrameWidth,
          mBackgroundColors, mBackgroundPositions, mBackgroundDirection
        )

        mBackgroundPaint.shader = mBackgroundShader
      } else {
        mBackgroundPaint.color = mBackgroundColor
      }


      mRectF = RectF(0f, 0f, width, height)
      mWidth = width
      mHeight = height


    }

    canvas.drawPath(mOutPath, mFramePaint)
//    mFramePaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
//    canvas.drawPath(mInnerPath, mFramePaint)
//    mFramePaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    canvas.drawPath(mBackgroundPath, mBackgroundPaint)
    val layerId = canvas.save()
    canvas.restoreToCount(layerId)

  }

  private fun getLinearGradient(
    width: Float,
    height: Float,
    colors: IntArray,
    positions: FloatArray,
    direction: String
  ): LinearGradient {

    return if (!TextUtils.isEmpty(direction)) {
      when (direction) {
        "toBottom" -> LinearGradient(0f, 0f, 0f, height, colors, positions, Shader.TileMode.REPEAT)
        "toTop" -> LinearGradient(0f, height, 0f, 0f, colors, positions, Shader.TileMode.REPEAT)
        "toLeft" -> LinearGradient(width, 0f, 0f, 0f, colors, positions, Shader.TileMode.REPEAT)
        "toRight" -> LinearGradient(0f, 0f, width, 0f, colors, positions, Shader.TileMode.REPEAT)
        "toLeftTop" -> LinearGradient(
          width,
          height,
          0f,
          0f,
          colors,
          positions,
          Shader.TileMode.REPEAT
        )
        "toRightTop" -> LinearGradient(
          0f,
          height,
          width,
          0f,
          colors,
          positions,
          Shader.TileMode.REPEAT
        )
        "toLeftBottom" -> LinearGradient(
          width,
          0f,
          0f,
          height,
          colors,
          positions,
          Shader.TileMode.REPEAT
        )
        "toRightBottom" -> LinearGradient(
          0f,
          0f,
          width,
          height,
          colors,
          positions,
          Shader.TileMode.REPEAT
        )
        else -> LinearGradient(0f, 0f, 0f, height, colors, positions, Shader.TileMode.REPEAT)
      }
    } else {
      LinearGradient(0f, 0f, 0f, height, colors, positions, Shader.TileMode.REPEAT)
    }
  }

  override fun setAlpha(p0: Int) {
  }

  override fun getOpacity() = PixelFormat.UNKNOWN

  override fun setColorFilter(p0: ColorFilter?) {
  }


}
