package person

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import model.Person
import model.Planet
import network.NetworkClient

/**
 * Created by hani.fakhouri on 2023-07-30.
 */

data class PersonScreenState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val planetData: Planet? = null,
)

class PersonScreenVm(
    private val person: Person,
) : ScreenModel {

    private val _uiState = MutableStateFlow(PersonScreenState())
    val uiState: StateFlow<PersonScreenState> = _uiState.asStateFlow()

    private val httpClient: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
                socketTimeoutMillis = 60_000
            }
        }
    }

    init {
        loadData()
    }

    fun loadData() {
        if (_uiState.value.loading) {
            return
        }
        _uiState.update {
            it.copy(
                loading = true,
                error = false,
            )
        }
        coroutineScope.launch {
            try {
                val planet = NetworkClient.call<Planet>(person.homeworld)
                _uiState.update {
                    it.copy(
                        loading = false,
                        error = false,
                        planetData = planet,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        error = true,
                    )
                }
            }
        }
    }


}