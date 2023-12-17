package utils

import android.util.Log

/**
 * Created by hani.fakhouri on 2023-07-29.
 */
actual class AppLogger actual constructor() {

    actual fun log(message: String) {
        Log.d("hanilogx", "KMPLOG: $message")
    }

}