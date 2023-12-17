import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import people.PeopleScreen
import theme.KmpAppTheme
import transitions.ScreenSlideInTransition

@Composable
fun App() {
    KmpAppTheme {

        Navigator(PeopleScreen) { navigator ->
            ScreenSlideInTransition(navigator)
        }

        // POC
        // Keep ScreenA, ScreenB and ScreenC for reference
        /*
        Navigator(ScreenA) { navigator ->
            ScreenSlideInTransition(navigator)
        }
        */

    }
}

expect fun getPlatformName(): String