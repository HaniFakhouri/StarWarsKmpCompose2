package person

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.myapplication.common.MR
import components.ErrorUi
import components.LoadingUi
import dev.icerock.moko.resources.compose.fontFamilyResource
import model.Person
import model.Planet

/**
 * Created by hani.fakhouri on 2023-07-30.
 */
data class PersonScreen(
    val person: Person,
) : Screen {

    @Composable
    override fun Content() {
        val vm = rememberScreenModel { PersonScreenVm(person) }
        val state by vm.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .background(color = Color.Black)
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            Text(
                text = "${person.name.uppercase()}",
                fontWeight = FontWeight.W700,
                fontSize = 28.sp,
                fontFamily = fontFamilyResource(MR.fonts.starjhol.regular),
                color = Color.White
            )
            Spacer(Modifier.size(16.dp))

            Text(text = "Gender: ${person.gender}", color = Color.White)
            Spacer(Modifier.size(4.dp))

            Text(text = "Height: ${person.height} cm", color = Color.White)
            Spacer(Modifier.size(4.dp))

            Text(text = "Mass: ${person.mass} kg", color = Color.White)
            Spacer(Modifier.size(4.dp))

            Text(text = "Eye color: ${person.eye_color}", color = Color.White)
            Spacer(Modifier.size(4.dp))

            Text(text = "Hair color: ${person.hair_color}", color = Color.White)
            Spacer(Modifier.size(4.dp))

            Text(text = "Skin color: ${person.skin_color}", color = Color.White)
            Spacer(Modifier.size(4.dp))

            Text(text = "Birth year: ${person.birth_year}", color = Color.White)

            Spacer(Modifier.size(24.dp))
            when {
                state.loading -> LoadingUi()
                state.error -> ErrorUi(vm::loadData)
                else -> {
                    state.planetData?.let { planetData ->
                        PlanetUi(planetData)
                    }
                }
            }

            Spacer(Modifier.size(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black,
                ),
                onClick = {
                    navigator.pop()
                }
            ) {
                Text("Back")
            }
        }
    }

}

@Composable
private fun PlanetUi(planet: Planet) {
    Text(
        text = "Planet: ${planet.name.uppercase()}",
        fontWeight = FontWeight.W700,
        style = MaterialTheme.typography.h6,
        color = Color.White
    )
    Spacer(Modifier.size(16.dp))

    Text(text = "Population: ${planet.population}", color = Color.White)
    Spacer(Modifier.size(4.dp))

    Text(text = "Climate: ${planet.climate}", color = Color.White)
    Spacer(Modifier.size(4.dp))

    Text(text = "Gravity: ${planet.gravity}", color = Color.White)
    Spacer(Modifier.size(4.dp))

    Text(text = "Terrain: ${planet.terrain}", color = Color.White)


}