package space.reul.cleanarchitectureexample.app.ui.composables

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import space.reul.cleanarchitectureexample.app.ui.theme.CleanArchitectureExampleTheme
import space.reul.cleanarchitectureexample.domain.model.Event
import space.reul.cleanarchitectureexample.domain.model.EventList

@Composable
fun EventGrid(eventList: EventList, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp)
    ) {
        val urls = eventList.events.mapNotNull { it.images?.firstOrNull()?.url }
        urls.forEach { url ->
            item { AsyncImageCell(url) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventGridPreview() {
    CleanArchitectureExampleTheme {
        ImageGrid(listOf("https://cdn.shibe.online/shibes/36083b6b1f07865085681235e4b4b174f60b7db1.jpg"))
    }
}
