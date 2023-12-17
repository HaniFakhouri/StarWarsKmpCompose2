package di

import org.koin.core.context.startKoin

/**
 * Created by hani.fakhouri on 2023-07-29.
 */
class Helper {

    fun initKoin() {
        startKoin {
            modules(appModule())
        }
    }

}