package space.reul.ticketmasterchallenge.app.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun AsyncImageCell(modifier: Modifier = Modifier, url: String) {
    Card(modifier.fillMaxHeight()) {
        Row {
            AsyncImage(
                modifier = modifier.fillMaxHeight(),
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = BrushPainter(
                    Brush.linearGradient(
                        listOf(
                            Color(color = 0xFFFFFFFF),
                            Color(color = 0xFF222222),
                        )
                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun AsyncImageCellPreview() {
    AsyncImageCell(url="")
}
