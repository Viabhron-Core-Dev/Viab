package com.nexus.launcher.overlay

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager

/**
 * HandleOverlay: The invisible/translucent edge trigger for the Sidebar.
 */
class HandleOverlay(private val context: Context) : View.OnTouchListener {

    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val params = WindowManager.LayoutParams(
        WindowManager.LayoutParams.WRAP_CONTENT,
        400, // Handle height
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT
    ).apply {
        gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
    }

    private val handleView = View(context).apply {
        setBackgroundColor(0x33FFFFFF) // Default 20% opacity
        setOnTouchListener(this@HandleOverlay)
    }

    fun show() {
        windowManager.addView(handleView, params)
    }

    fun hide() {
        windowManager.removeView(handleView)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Detected edge touch - potentially trigger "Nexus Sidebar"
            }
        }
        return true
    }
}
