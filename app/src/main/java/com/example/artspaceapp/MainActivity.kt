package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

data class Artwork(
    val imageRes: Int,
    val title: String,
    val author: String,
    val year: String
)

val artworks = listOf(
    Artwork(R.drawable.starrynight, "The Starry Night", "Vincent van Gogh", "1889"),
    Artwork(R.drawable.thescream, "The Scream", "Edvard Munch", "1893"),
    Artwork(R.drawable.monalisa, "Mona Lisa", "Leonardo da Vinci", "1503")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ArtSpaceScreen() // màn hình tương tác
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var index by remember { mutableStateOf(0) }
    val current = artworks[index]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(current)
        ArtworkInfoCard(current)
        ControlBar(
            onPrevious = {
                index = when (index) {
                    0 -> artworks.lastIndex        // nếu đang ở đầu thì quay về cuối
                    else -> index - 1              // còn lại thì lùi 1
                }
            },
            onNext = {
                index = when (index) {
                    artworks.lastIndex -> 0        // nếu đang ở cuối thì quay về đầu
                    else -> index + 1              // còn lại thì tiến 1
                }
            }
        )
    }
}


@Composable
fun ControlBar(
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevious,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.width(140.dp)
        ) { Text("Previous") }

        Button(
            onClick = onNext,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.width(140.dp)
        ) { Text("Next") }
    }
}

@Composable
fun ArtworkWall(artwork: Artwork) {
    Surface(
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = artwork.title, // có mô tả trợ năng
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // giữ khung vuông như bạn đang dùng
            )
        }
    }
}

@Composable
fun ArtworkInfoCard(artwork: Artwork) {
    Surface(
        tonalElevation = 1.dp,
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = artwork.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = artwork.author,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "  (${artwork.year})",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewArtSpace() {
    ArtSpaceAppTheme {
        ArtSpaceScreen()
    }
}
