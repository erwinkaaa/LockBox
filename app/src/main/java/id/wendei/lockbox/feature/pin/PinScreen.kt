package id.wendei.lockbox.feature.pin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.replaceAll
import id.wendei.lockbox.R
import id.wendei.lockbox.core.composable.ConfirmDialog
import id.wendei.lockbox.core.composable.noRippleClickable
import id.wendei.lockbox.core.navigation.AppDestination
import id.wendei.lockbox.core.util.ScreenWrapper
import kotlinx.coroutines.launch

enum class PinScreenType {
    Register,
    RegisterConfirmation,
    Verify
}

@Composable
fun PinScreen(
    pin: String = "",
    type: PinScreenType = PinScreenType.Verify
) {
    ScreenWrapper<PinViewModel, PinState, PinEvent, PinIntent>(
        events = { events, scope, navController ->
            scope.launch {
                events.collect { event ->
                    when (event) {
                        is PinEvent.NavigateToConfirmPin ->
                            navController.navigate(
                                AppDestination.Pin(
                                    pin = event.pin,
                                    type = PinScreenType.RegisterConfirmation
                                )
                            )

                        is PinEvent.Back ->
                            navController.replaceAll(
                                AppDestination.Pin(type = PinScreenType.Register)
                            )

                        is PinEvent.NavigateToMain ->
                            navController.replaceAll(AppDestination.Main)
                    }
                }
            }
        },
        content = { viewModel, state ->
            LaunchedEffect(key1 = Unit) {
                viewModel.onIntent(PinIntent.Init(pin = pin, type = type))
            }
            PinView(
                type = type,
                state = state,
                onIntent = viewModel::onIntent
            )
            if (state.showRegisterPopUp) {
                ConfirmDialog(
                    title = "Information!",
                    message = "You can only set this PIN once!\nWe recommend you to use your common PIN.",
                    confirmText = "",
                    onConfirm = { },
                    onClose = {
                        viewModel.onIntent(PinIntent.ShowRegisterPopUp(show = false))
                    }
                )
            }
        }
    )
}

@Composable
private fun PinView(
    type: PinScreenType,
    state: PinState,
    onIntent: (PinIntent) -> Unit
) {
    LaunchedEffect(state.pin) {
        if (state.pin.length == 6) {
            onIntent.invoke(PinIntent.Submit)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.lock_box),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = when (type) {
                PinScreenType.Register -> "Register LockBox PIN"
                PinScreenType.RegisterConfirmation -> "Confirm LockBox PIN"
                PinScreenType.Verify -> "Input LockBox PIN"
            },
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier,
            text = when (type) {
                PinScreenType.Register -> "Input 6 digits PIN Number to register."
                PinScreenType.RegisterConfirmation -> "Input your 6 digits PIN Number to verify."
                PinScreenType.Verify -> "Input 6 digits PIN Number to access in."
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PinPadView(
            modifier = Modifier
                .weight(1f),
            state = state,
            listPin = state.listPin,
            onInputPin = {
                onIntent.invoke(PinIntent.UpdatePin(pin = it))
            },
            onDeletePin = {
                onIntent.invoke(PinIntent.DeletePin)
            },
            onBack = {
                onIntent.invoke(PinIntent.Back)
            }
        )
    }
}

@Composable
private fun PinPadView(
    modifier: Modifier = Modifier,
    state: PinState,
    listPin: List<String> = listOf("", "", "", "", "", ""),
    onInputPin: (String) -> Unit,
    onDeletePin: () -> Unit,
    onBack: () -> Unit
) {
    val listNumber = (1..12).map { it.toString() }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyRow {
            items(listPin.size) { index ->
                Image(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    painter = if (listPin[index].isEmpty())
                        painterResource(id = R.drawable.dot_gray)
                    else
                        painterResource(id = R.drawable.dot_black),
                    contentDescription = null
                )
            }
        }
        if (state.isError) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = state.errorMessage,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            content = {
                items(listNumber.size) { index ->
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(CircleShape)
                            .clickable(
                                onClick = {
                                    onInputPin(
                                        if (index < 10) {
                                            listNumber[index]
                                        } else if (index == 10) {
                                            "0"
                                        } else {
                                            ""
                                        }
                                    )
                                },
                                enabled = index != 9
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (index == 9) {
                            if (state.type == PinScreenType.RegisterConfirmation) {
                                Image(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .noRippleClickable(
                                            onClick = onBack
                                        ),
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        } else if (index == 11) {
                            Image(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .noRippleClickable(
                                        onClick = onDeletePin
                                    ),
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = null
                            )
                        } else {
                            Text(
                                text = if (index == 10) "0" else listNumber[index],
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun PinRegisterPreview() {
    PinView(
        type = PinScreenType.Register,
        state = PinState(
            type = PinScreenType.Register
        ),
        onIntent = {

        }
    )
}

@Preview
@Composable
private fun PinRegisterConfirmationPreview() {
    PinView(
        type = PinScreenType.RegisterConfirmation,
        state = PinState(
            type = PinScreenType.RegisterConfirmation
        ),
        onIntent = {

        }
    )
}

@Preview
@Composable
private fun PinVerifyPreview() {
    PinView(
        type = PinScreenType.Verify,
        state = PinState(
            type = PinScreenType.Verify
        ),
        onIntent = {

        }
    )
}