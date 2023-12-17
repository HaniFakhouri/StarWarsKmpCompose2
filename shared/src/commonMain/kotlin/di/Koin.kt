package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import repo.IRepo
import repo.RepoImpl
import utils.AppLogger

/**
 * Created by hani.fakhouri on 2023-07-29.
 */
fun appModule() = listOf(commonModule())

fun commonModule() = module {

    singleOf(::RepoImpl) bind IRepo::class

    single {
        AppLogger()
    }

}