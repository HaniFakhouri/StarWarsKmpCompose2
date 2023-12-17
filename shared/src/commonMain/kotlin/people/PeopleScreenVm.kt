package people

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
import model.GetPeopleResponse
import model.Person
import network.NetworkClient
import org.jetbrains.compose.resources.load

/**
 * Created by hani.fakhouri on 2023-07-29.
 */

data class PeopleScreenState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val people: List<Person> = emptyList(),
)

class PeopleScreenVm : ScreenModel {

    private val _uiState = MutableStateFlow(PeopleScreenState())
    val uiState: StateFlow<PeopleScreenState> = _uiState.asStateFlow()

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

    override fun onDispose() {
        super.onDispose()
        httpClient.close()
    }

    fun loadData() {
        _uiState.update {
            it.copy(
                loading = true,
                error = false,
            )
        }
        coroutineScope.launch {
            _uiState.update {
                it.copy(
                    loading = false,
                    people = getPeople(),
                )
            }
        }
    }

    private suspend fun getPeople(): List<Person> {
        val people = mutableListOf<Person>()
        try {
            with(people) {
                addAll(NetworkClient.call<GetPeopleResponse>("https://swapi.dev/api/people").results)
                /*
                addAll(NetworkClient.call<GetPeopleResponse>("https://swapi.dev/api/people/?page=2").results)
                addAll(NetworkClient.call<GetPeopleResponse>("https://swapi.dev/api/people/?page=3").results)
                addAll(NetworkClient.call<GetPeopleResponse>("https://swapi.dev/api/people/?page=4").results)
                */
            }
        } catch (e: Exception) {
            println("hanilogx: ERROR: $e")
            _uiState.update {
                it.copy(error = true)
            }
        }
        return people
    }

}