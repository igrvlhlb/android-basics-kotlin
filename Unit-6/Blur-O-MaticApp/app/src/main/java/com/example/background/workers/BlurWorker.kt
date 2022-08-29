package com.example.background.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import com.example.background.R

private const val TAG = "BlurWorker"

class BlurWorker(val ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        Log.i(TAG, "ctx[$ctx] =? appContext[$appContext]")
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        makeStatusNotification("Image blurring in progress", appContext)
        return try {
            if (TextUtils.isEmpty(resourceUri)) {
                Log.e(TAG, "Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }
            val picture = BitmapFactory.decodeStream(
                appContext.contentResolver.openInputStream(Uri.parse(resourceUri)))
            val blurredPicture = blurBitmap(picture, appContext)
            val outputUri = writeBitmapToFile(appContext, blurredPicture)
            makeStatusNotification("Outiput is in $outputUri", appContext)
            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
            Result.success(outputData)
        } catch (e: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}