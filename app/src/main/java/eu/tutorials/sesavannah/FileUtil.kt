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
