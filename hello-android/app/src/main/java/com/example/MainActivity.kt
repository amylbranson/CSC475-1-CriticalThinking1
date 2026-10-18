package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.HelloAndroidTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val systemInDarkTheme = isSystemInDarkTheme()
      var isDarkTheme by remember { mutableStateOf<Boolean?>(null) }
      val currentDarkTheme = isDarkTheme ?: systemInDarkTheme

      HelloAndroidTheme(darkTheme = currentDarkTheme) {
        HelloAndroidApp(
          isDarkTheme = currentDarkTheme,
          onToggleTheme = {
            isDarkTheme = !currentDarkTheme
          },
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloAndroidApp(
  isDarkTheme: Boolean,
  onToggleTheme: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleMedium,
          )
        },
        actions = {
          IconButton(
            onClick = onToggleTheme,
            modifier = Modifier.testTag("theme_toggle_button"),
          ) {
            Icon(
              imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
              contentDescription = if (isDarkTheme) "Switch to light theme" else "Switch to dark theme",
            )
          }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.surface,
          titleContentColor = MaterialTheme.colorScheme.onSurface,
          actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
      )
    },
    containerColor = MaterialTheme.colorScheme.background,
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(horizontal = 24.dp),
      contentAlignment = Alignment.Center,
    ) {
      HelloAndroidContent(
        modifier = Modifier
          .widthIn(max = 480.dp)
          .fillMaxWidth(),
      )
    }
  }
}

@Composable
fun HelloAndroidContent(modifier: Modifier = Modifier) {
  Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.extraLarge,
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 48.dp, horizontal = 24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Surface(
        modifier = Modifier.size(72.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            imageVector = Icons.Default.WavingHand,
            contentDescription = "Wave greeting icon",
            modifier = Modifier.size(36.dp),
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = stringResource(id = R.string.greeting_text),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        modifier = Modifier.testTag("greeting_text"),
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Built with Kotlin, Jetpack Compose & Material 3",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
      )
    }
  }
}

@Preview(showBackground = true, name = "Light Theme")
@Composable
fun HelloAndroidAppLightPreview() {
  HelloAndroidTheme(darkTheme = false) {
    HelloAndroidApp(isDarkTheme = false, onToggleTheme = {})
  }
}

@Preview(showBackground = true, name = "Dark Theme")
@Composable
fun HelloAndroidAppDarkPreview() {
  HelloAndroidTheme(darkTheme = true) {
    HelloAndroidApp(isDarkTheme = true, onToggleTheme = {})
  }
}
