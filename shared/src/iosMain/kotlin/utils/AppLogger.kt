package utils

/**
 * Created by hani.fakhouri on 2023-07-29.
 */
actual class AppLogger actual constructor() {

    actual fun log(message: String) {
        print("hanilogx: $message")
    }

}