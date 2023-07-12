package ro.alexmamo.firestorechatgpt.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText

class Utils {
    companion object {
        fun print(e: Exception?) = e?.let {
            Log.e(TAG, it.stackTraceToString())
        }

        fun showMessage(
            context: Context,
            message: String
        ) = makeText(context, message, LENGTH_LONG).show()
    }
}