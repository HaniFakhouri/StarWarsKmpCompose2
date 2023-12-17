package repo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import utils.AppLogger

/**
 * Created by hani.fakhouri on 2023-07-29.
 */
class RepoImpl(
    private val logger: AppLogger
) : IRepo {

    init {
        logger.log("INIT REPO")
    }

    private var data: String = ""

    override fun save(data: String) {
        this.data = data
    }

    override fun getLatest(): String {
        return data
    }

    override fun ticker(): Flow<Int> {
        return flow {
            var counter = 0
            while (true) {
                emit(counter++)
                delay(1_000)
            }
        }
    }


}