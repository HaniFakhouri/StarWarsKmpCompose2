package screens

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repo.IRepo
import repo.InjectedRepo

/**
 * Created by hani.fakhouri on 2023-07-29.
 */

data class ScreenAState(
    val loading: Boolean = false,
    val data: String = "",
    val counter: Int = 0,
)

class ScreenAVm(
    private val repo: IRepo,
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenAState())
    val uiState: StateFlow<ScreenAState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(loading = true)
        }
        coroutineScope.launch {
            repo.save("Repo data")
            delay(2_000)
            _uiState.update {
                it.copy(
                    loading = false,
                    data = "Screen A data loaded: ${repo.getLatest()}"
                )
            }
        }
    }

    private var ticksJob: Job? = null

    fun startCollectingTicks() {
        ticksJob?.cancel()
        ticksJob = coroutineScope.launch {
            repo.ticker().collect { tick ->
                _uiState.update {
                    it.copy(
                        counter = tick
                    )
                }
            }
        }
    }

    fun stopCollectingTicks() {
        ticksJob?.cancel()
    }

    companion object {
        val injected : ScreenAVm =  ScreenAVm(
            repo = InjectedRepo.repo
        )
    }

}