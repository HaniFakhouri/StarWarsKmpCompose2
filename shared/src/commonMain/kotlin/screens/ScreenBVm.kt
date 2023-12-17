package screens

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import repo.IRepo

/**
 * Created by hani.fakhouri on 2023-07-30.
 */

data class ScreenBState(
    val loading: Boolean = false,
    val data: String = "",
)



class ScreenBVm : ScreenModel, KoinComponent {

    private val repo: IRepo by inject()

    private val _uiState = MutableStateFlow(ScreenBState())
    val uiState: StateFlow<ScreenBState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(loading = true)
        }
        coroutineScope.launch {
            repo.save("Repo data B")
            delay(2_000)
            _uiState.update {
                it.copy(
                    loading = false,
                    data = "Screen B data loaded: ${repo.getLatest()}"
                )
            }
        }
    }

}