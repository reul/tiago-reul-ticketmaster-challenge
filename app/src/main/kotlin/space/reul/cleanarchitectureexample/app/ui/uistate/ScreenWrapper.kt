package space.reul.cleanarchitectureexample.app.ui.uistate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import space.reul.cleanarchitectureexample.app.R
import space.reul.cleanarchitectureexample.app.ui.theme.Spacing
import space.reul.imglytrial.app.ui.state.UiState

/**
 * Utility composable that handles [UiState] to display:
 * - A [FullScreenProgressIndicator], if [uiState] is [UiState.Loading].
 * - An [ErrorView] , if [uiState] is [UiState.Failure].
 * - The [contents], if [uiState] is [UiState.Success].
 *
 * @param modifier The composable modifier.
 * @param uiState The state of the ui.
 * @param contents The contents to display in case of [UiState.Success].
 */
@Composable
fun <T> ScreenWrapper(
    modifier: Modifier = Modifier,
    uiState: UiState<T>,
    showDisconnectedMessage: Boolean = false,
    contents: @Composable (Modifier, T) -> Unit
) {
    Column {
        if (showDisconnectedMessage) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.error)
                    .padding(Spacing.regular)

            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Network unavailable",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onError,
                    style = MaterialTheme.typography.titleMedium
                )

            }
        }

        Box(modifier.weight(1f)) {
            when (uiState) {
                is UiState.Failure -> ErrorView(
                    modifier = Modifier.fillMaxSize(),
                    message = stringResource(id = R.string.generic_error),
                    exception = uiState.exception
                )

                UiState.Loading -> FullScreenProgressIndicator(Modifier.fillMaxSize())
                is UiState.Success -> contents(modifier, uiState.data)
            }
        }
    }
}

@Preview
@Composable
fun ScreenWrapperPreview() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            ScreenWrapper(modifier = Modifier.fillMaxSize(),
                uiState = UiState.Success(Unit),
                showDisconnectedMessage = true,
                contents = { modifier, _ ->
                    Box(modifier = modifier) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Contents",
                            textAlign = TextAlign.Center
                        )
                    }
                })
        }
    }
}