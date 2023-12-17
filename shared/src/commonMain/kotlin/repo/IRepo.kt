package repo

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by hani.fakhouri on 2023-07-29.
 */
interface IRepo {
    fun save(data: String)
    fun getLatest(): String
    fun ticker(): Flow<Int>
}

object InjectedRepo : KoinComponent {
    val repo: IRepo by inject()
}
