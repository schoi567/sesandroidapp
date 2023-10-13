package eu.tutorials.sesavannah
import android.util.Log
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.provider.MediaStore
import java.io.File

object FileUtil {
    fun getPathFromUri(context: Context, contentUri: Uri?): String? {
        val cacheDir = context.cacheDir
        val tempFile = File.createTempFile("prefix", "extension", cacheDir)
        tempFile.outputStream().use { cacheOut ->
            context.contentResolver.openInputStream(contentUri!!)?.use { stream ->
                stream.copyTo(cacheOut)
            }
        }
        return tempFile.absolutePath
    }


    }

/*
    fun getPathFromUri(context: Context, contentUri: Uri?): String? {
        if (contentUri == null) return null
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) ?: 0
            cursor?.moveToFirst()
            return cursor?.getString(column_index)
        } catch (e: Exception) {
            Log.e("FileUtil", "Error getting path", e)
            cursor?.close()
            return null
        } finally {
            cursor?.close()
        }

        */

/*
package eu.tutorials.sesavannah

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.provider.MediaStore
import java.io.File

object FileUtil {


    fun getPathFromUri(context: Context, contentUri: Uri?): String? {
        if (contentUri == null) return null

        // Check for MediaStore first. This will cover downloads, and media (e.g., images, audio, and video).
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        context.contentResolver.query(contentUri, projection, null, null, null)?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return it.getString(columnIndex)
            }
        }

        // Fallback: Try to copy the content stream to a local file and return its path.
        try {
            val filename = System.currentTimeMillis().toString() + ".pdf"
            val cacheFile = File(context.cacheDir, filename)
            context.contentResolver.openInputStream(contentUri)?.use { inputStream ->
                val outputStream = cacheFile.outputStream()
                inputStream.copyTo(outputStream)
            }
            return cacheFile.path
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }



    fun getPathFromUri1(context: Context, contentUri: Uri?): String? {
        if (contentUri == null) return null

        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        } catch (e: Exception) {
            val result: String? = null
            val returnCursor = context.contentResolver.query(contentUri, null, null, null, null)
            if (returnCursor != null) {
                val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                returnCursor.moveToFirst()
                val name = returnCursor.getString(nameIndex)
                val path = context.filesDir.toString() + "/" + name
                try {
                    val inputStream = context.contentResolver.openInputStream(contentUri)
                    val outputStream = File(path).outputStream()
                    inputStream?.copyTo(outputStream)
                    inputStream?.close()
                    outputStream.close()
                    return path
                } catch (e: Exception) {
                    return result
                }
            }
            return result
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }
}
*/
