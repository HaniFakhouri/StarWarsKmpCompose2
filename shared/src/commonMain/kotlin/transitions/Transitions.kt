package transitions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransitionContent

/**
 * Created by hani.fakhouri on 2023-07-29.
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ScreenSlideInTransition(
    navigator: Navigator,
    content: ScreenTransitionContent = { it.Content() },
) {
    val animationSpec: FiniteAnimationSpec<IntOffset> = spring(
        stiffness = Spring.StiffnessMediumLow,
        visibilityThreshold = IntOffset.VisibilityThreshold
    )
    val (initialOffset, targetOffset) = when (navigator.lastEvent) {
        StackEvent.Pop -> ({ size: Int -> -size }) to ({ size: Int -> size })
        else -> ({ size: Int -> size }) to ({ size: Int -> -size })
    }
    AnimatedContent(
        targetState = navigator.lastItem,
        transitionSpec = {
            slideInHorizontally(animationSpec, initialOffset) with
                    slideOutHorizontally(animationSpec, targetOffset)
        },
        modifier = Modifier.fillMaxSize(),
    ) { screen ->
        navigator.saveableState(key = "transition", screen) {
            content(screen)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ScreenFadeTransition(
    navigator: Navigator,
    content: ScreenTransitionContent = { it.Content() },
) {
    val animationSpec: FiniteAnimationSpec<Float> = spring(stiffness = Spring.StiffnessMediumLow)
    AnimatedContent(
        targetState = navigator.lastItem,
        transitionSpec = {
            fadeIn(animationSpec = animationSpec) with
                    fadeOut(animationSpec = animationSpec)
        },
        modifier = Modifier.fillMaxSize(),
    ) { screen ->
        navigator.saveableState(key = "transition", screen) {
            content(screen)
        }
    }
}