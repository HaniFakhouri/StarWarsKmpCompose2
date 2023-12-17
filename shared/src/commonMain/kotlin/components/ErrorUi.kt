package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by hani.fakhouri on 2023-07-30.
 */
@Composable
fun ErrorUi(
    retry: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Error loading", color = MaterialTheme.colors.error)
        Spacer(Modifier.size(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = retry
        ) {
            Text("Try again")
        }
    }
}