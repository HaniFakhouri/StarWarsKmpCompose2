package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapplication.common.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import theme.galacticBlack
import theme.yodaGreen

/**
 * Created by hani.fakhouri on 2023-07-30.
 */

@Composable
internal fun LoadingUi(
    loadingText: String = "Loading",
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(galacticBlack).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            color = yodaGreen,
            text = loadingText,
            fontFamily = fontFamilyResource(MR.fonts.starjhol.regular),
        )
        Spacer(Modifier.size(8.dp))
        CircularProgressIndicator(
            modifier = Modifier.size(16.dp),
            strokeWidth = 2.dp,
            color = yodaGreen,
        )
    }
}