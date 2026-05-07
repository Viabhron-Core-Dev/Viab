package com.nexus.launcher.logic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap

/**
 * IconProcessor: Optimized icon scaling for low-RAM devices (3GB).
 * Ensures all icons are cached at a uniform low resolution (48dp).
 */
class IconProcessor(private val context: Context) {

    private val targetSize = (48 * context.resources.displayMetrics.density).toInt()

    fun process(drawable: Drawable): Bitmap {
        // Downsample for memory safety
        val bitmap = drawable.toBitmap(targetSize, targetSize, Bitmap.Config.ARGB_8888)
        return bitmap
    }
    
    fun getAdaptiveIcon(packageName: String): Drawable? {
        return try {
            context.packageManager.getApplicationIcon(packageName)
        } catch (e: Exception) {
            null
        }
    }
}
