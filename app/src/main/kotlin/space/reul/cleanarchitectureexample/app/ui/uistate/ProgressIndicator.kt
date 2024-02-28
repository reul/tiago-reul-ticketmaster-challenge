package space.reul.cleanarchitectureexample.app.ui.uistate

import androidx.annotation.RestrictTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 *
 */
@Composable
fun FullScreenProgressIndicator(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box {
            CircularProgressIndicator(
                Modifier
                    .width(48.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@RestrictTo(RestrictTo.Scope.TESTS)
@Preview
@Composable
fun FullScreenProgressIndicatorPreview() {
    MaterialTheme {
        FullScreenProgressIndicator()
    }
}