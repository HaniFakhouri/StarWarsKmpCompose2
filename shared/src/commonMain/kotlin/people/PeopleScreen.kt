package people

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import person.PersonScreen
import theme.crawlYellow
import theme.rebelAllianceOrange

/**
 * Created by hani.fakhouri on 2023-07-29.
 */

object PeopleScreen : Screen {
    @Composable
    override fun Content() {
        val vm = rememberScreenModel { PeopleScreenVm() }
        val state by vm.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        when {
            state.loading -> LoadingUi()
            state.error -> ErrorUi(vm::loadData)
            else -> PeopleUi(
                people = state.people,
                onOpenPerson = {
                    navigator.push(PersonScreen(it))
                }
            )
        }
    }
}

@Composable
private fun PeopleUi(
    people: List<Person>,
    onOpenPerson: (Person) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(color = rebelAllianceOrange),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(people) { person ->
            PersonUi(
                person = person,
                onClick = {
                    onOpenPerson(person)
                }
            )
        }
    }
}

@Composable
private fun PersonUi(
    person: Person,
    onClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        backgroundColor = crawlYellow,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = person.name.uppercase(),
                fontWeight = FontWeight.W500,
                fontSize = 28.sp,
                fontFamily = fontFamilyResource(MR.fonts.starjhol.regular),
            )
            Spacer(Modifier.size(12.dp))

            Text(
                text = "Gender: ${person.gender}",
                fontWeight = FontWeight.W500,
            )

            Spacer(Modifier.size(4.dp))
            Text(
                text = "Height: ${person.height} cm",
                fontWeight = FontWeight.W500,
            )

            Spacer(Modifier.size(4.dp))
            Text(
                text = "Eye color: ${person.eye_color}",
                fontWeight = FontWeight.W500,
            )
        }
    }
}