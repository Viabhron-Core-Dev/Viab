package com.nexus.launcher.ui.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.CycleInterpolator

/**
 * VisualUtils: Shared UI helpers for animations.
 */
object VisualUtils {

    /**
     * Wiggle animation for edit mode.
     */
    fun startWiggle(view: View) {
        val rotate = PropertyValuesHolder.ofFloat(View.ROTATION, -1f, 1f)
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.95f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.95f)
        
        ObjectAnimator.ofPropertyValuesHolder(view, rotate, scaleX, scaleY).apply {
            duration = 150
            repeatCount = ObjectAnimator.INFINITE
            interpolator = CycleInterpolator(1f)
            start()
        }
    }

    fun stopWiggle(view: View) {
        view.animate().rotation(0f).scaleX(1f).scaleY(1f).setDuration(200).start()
    }
}
