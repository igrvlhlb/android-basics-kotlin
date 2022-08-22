package com.example.background.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R

private const val TAG = "BlurWorker"

class BlurWorker(val ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        Log.i(TAG, "ctx[$ctx] =? appContext[$appContext]")
        makeStatusNotification("Image blurring in progress", appContext)
        return try {
            val picture = BitmapFactory.decodeResource(
                appContext.resources,
                R.drawable.android_cupcake
            )
            val blurredPicture = blurBitmap(picture, appContext)
            val outputUri = writeBitmapToFile(appContext, blurredPicture)
            makeStatusNotification("Outiput is in $outputUri", appContext)
            Result.success()
        } catch (e: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}