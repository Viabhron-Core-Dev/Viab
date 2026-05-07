package com.nexus.launcher.services

import android.app.Service
import android.content.Intent
import android.media.MediaRecorder
import android.os.IBinder
import android.util.Log
import java.io.File
import java.io.IOException

/**
 * CallRecordingService: Handles call audio capture.
 * Optimized for background performance on low-end devices.
 */
class CallRecordingService : Service() {

    private var recorder: MediaRecorder? = null
    private var isRecording = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        if (action == "START_RECORDING") {
            startRecording(intent.getStringExtra("PHONE_NUMBER") ?: "unknown")
        } else if (action == "STOP_RECORDING") {
            stopRecording()
        }
        return START_NOT_STICKY
    }

    private fun startRecording(phoneNumber: String) {
        if (isRecording) return

        val outputFile = File(getExternalFilesDir(null), "call_${phoneNumber}_${System.currentTimeMillis()}.amr")
        
        recorder = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            MediaRecorder(this)
        } else {
            MediaRecorder()
        }.apply {
            setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION) // High probability of working on modern Android
            setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(outputFile.absolutePath)
            
            try {
                prepare()
                start()
                isRecording = true
                Log.d("NexusRecorder", "Recording started: ${outputFile.name}")
            } catch (e: IOException) {
                Log.e("NexusRecorder", "Prepare failed", e)
            }
        }
    }

    private fun stopRecording() {
        if (!isRecording) return
        try {
            recorder?.stop()
            recorder?.release()
        } catch (e: Exception) {
            Log.e("NexusRecorder", "Stop failed", e)
        } finally {
            recorder = null
            isRecording = false
            stopSelf()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
