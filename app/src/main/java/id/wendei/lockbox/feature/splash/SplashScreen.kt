package id.wendei.lockbox.feature.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import id.wendei.lockbox.R
import id.wendei.lockbox.core.navigation.AppRoutes
import id.wendei.lockbox.core.navigation.replace
import id.wendei.lockbox.core.util.ScreenWrapper
import kotlinx.coroutines.launch

@Composable
fun SplashScreen() {
    ScreenWrapper<SplashViewModel, SplashState, SplashEvent, SplashIntent>(
        events = { events, scope, navController ->
            scope.launch {
                events.collect { event ->
                    when (event) {
                        is SplashEvent.NavigateToPin ->
                            navController.replace(AppRoutes.Pin)
                    }
                }
            }
        },
        content = { viewModel, state ->
            LaunchedEffect(Unit) {
                viewModel.onIntent(SplashIntent.Init)
            }
            SplashView(
                state = state,
                onIntent = viewModel::onIntent
            )
        }
    )
}

@Composable
private fun SplashView(
    state: SplashState,
    onIntent: (SplashIntent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center),
            visible = state.isLogoShow,
            enter = slideInVertically(
                animationSpec = tween(500),
                initialOffsetY = { it / 2 }
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lock_box),
                    contentDescription = stringResource(id = R.string.app_name)
                )
                if (!state.isTitleShow) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.Black,
                        fontSize = 24.sp
                    )
                }
                AnimatedVisibility(
                    visible = state.isTitleShow,
                    enter = slideInVertically(
                        animationSpec = tween(500),
                        initialOffsetY = { it / 2 }
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SplashPreview() {
    SplashView(
        state = SplashState(),
        onIntent = {

        }
    )
}