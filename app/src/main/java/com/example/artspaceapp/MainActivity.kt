package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme   // ✅ thêm theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()                                   // ✅ thêm import cho hàm này (dòng dưới)
        setContent {
            ArtSpaceAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ArtSpaceScreenStatic()                   // ✅ gọi màn hình tĩnh
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreenStatic() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall()       // vùng ảnh (tĩnh)
        ArtworkInfoCard()   // thẻ thông tin
        ControlBarStatic()  // hàng nút (tĩnh)
    }
}

@Composable
fun ControlBarStatic() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* no-op: bước 2 chỉ UI tĩnh */ },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.width(140.dp)
        ) { Text("Previous") }

        Button(
            onClick = { /* no-op: bước 2 chỉ UI tĩnh */ },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.width(140.dp)
        ) { Text("Next") }
    }
}

@Composable
fun ArtworkWall() {
    Surface(
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // khung trắng giống mock
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: thay ảnh thật
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // khung vuông gọn gàng
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ArtworkInfoCard() {
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
                text = "Still Life of Blue Rose and Other Flowers",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Owen Scott",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "  (2021)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewArtSpaceStatic() {
    ArtSpaceAppTheme {
        ArtSpaceScreenStatic()
    }
}
