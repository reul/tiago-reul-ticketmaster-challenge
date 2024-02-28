package space.reul.cleanarchitectureexample.app.ui.uistate

import androidx.annotation.RestrictTo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import space.reul.cleanarchitectureexample.app.R
import space.reul.cleanarchitectureexample.app.ui.theme.Spacing
import space.reul.imglytrial.app.ui.shared.toggle

/**
 * A simple error view including a warning icon, a message and a optional exception.
 * If a exception is provided, stacktrace details can be expanded.
 * @param modifier The composable modifier.
 * @param message An error message.
 * @param exception Optional exception.
 */
@Composable
fun ErrorView(modifier: Modifier = Modifier, message: String, exception: Exception? = null) {
    val showDetails = remember { mutableStateOf(false) }
    Surface(modifier.fillMaxSize()) {
        Column(
            Modifier.padding(top = Spacing.triple)
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = Spacing.double)
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally),
                imageVector = Icons.Default.Warning,
                tint = MaterialTheme.colorScheme.error,
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Spacing.half),
                text = message,
                color = MaterialTheme.colorScheme.error
            )

            exception?.stackTraceToString()?.let {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { showDetails.toggle() }
                        .padding(Spacing.regular),
                    text = stringResource(id = if (!showDetails.value) R.string.show_stacktrace else R.string.hide_stacktrace),
                    color = MaterialTheme.colorScheme.tertiary,
                )

                if (showDetails.value)

                    Box(
                        modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(Spacing.double),
                            text = it
                        )
                    }

            }
        }
    }
}

@RestrictTo(RestrictTo.Scope.TESTS)
@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(
        message = stringResource(id = R.string.generic_error),
        exception = IllegalArgumentException("Illegal")
    )
}