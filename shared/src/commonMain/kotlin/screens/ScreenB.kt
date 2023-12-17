package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import repo.InjectedRepo

/**
 * Created by hani.fakhouri on 2023-07-29.
 */
data class ScreenB(
    val name: String,
    val age: Int,
): Screen {

    @Composable
    override fun Content() {

        val vm = rememberScreenModel { ScreenBVm() }

        val navigator = LocalNavigator.currentOrThrow
        val state by vm.uiState.collectAsState()
        when {
            state.loading -> LoadingUi()
            else -> {
                Column {
                    Text("Screen B. Name: $name, Age: $age")
                    Spacer(Modifier.size(8.dp))

                    Text(state.data)
                    Spacer(Modifier.size(8.dp))

                    Button({
                        navigator.push(ScreenC)
                    }) {
                        Text("Go To C")
                    }
                }
            }
        }
    }

    @Composable
    private fun LoadingUi() {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Text("Loading Star Wars people data")
            Spacer(Modifier.size(8.dp))
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                color = Color.Black,
                strokeWidth = 2.dp,
            )
        }
    }
}