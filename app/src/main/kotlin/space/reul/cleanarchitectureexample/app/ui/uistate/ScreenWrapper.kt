package space.reul.cleanarchitectureexample.app.ui.uistate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import space.reul.cleanarchitectureexample.app.R
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
    contents: @Composable (Modifier, T) -> Unit
) {
    when (val value = uiState) {
        is UiState.Failure -> ErrorView(
            modifier = modifier,
            message = stringResource(id = R.string.generic_error),
            exception = value.exception
        )

        UiState.Loading -> FullScreenProgressIndicator(modifier)
        is UiState.Success -> contents(modifier, value.data)
    }
}
