package ms.gamehouse.zicare.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ms.gamehouse.zicare.R

@Composable
fun Error(
    message: String,
    modifier: Modifier = Modifier,
    appState: ZiAppState = rememberZiAppState(),
) {
    if (!appState.isOnline) {
        OfflineDialog(onRetry = appState::refreshOnline)
    } else {
        var showSnackbar by remember {
            mutableStateOf(true)
        }

        LaunchedEffect(key1 = showSnackbar) {
            if (showSnackbar) {
                delay(2000)
                showSnackbar = false
            }
        }

        if (showSnackbar) {
            androidx.compose.material.Snackbar(
                modifier = modifier.padding(16.dp),
                backgroundColor = colorResource(id = R.color.warning_color),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = message,
                    color = Color.Black
                )
            }

        }
    }
}