package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeexample.ui.theme.ComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExampleTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var clicks by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            text = "Olá fui pressionado $clicks vezes",
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(0.dp))
        )
        Button(
            colors = ButtonDefaults.buttonColors(Color.Magenta),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                clicks++
            }) {
            contentColorFor(backgroundColor = Color.Magenta)
            Image(
                painter = painterResource(id = R.drawable.ic_chat),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = "Click me",
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeExampleTheme {
        MainScreen()
    }
}