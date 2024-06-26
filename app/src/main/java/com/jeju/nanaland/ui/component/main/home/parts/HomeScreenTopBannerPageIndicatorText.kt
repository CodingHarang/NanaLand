package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun RowScope.HomeScreenTopBannerPageIndicatorText(text: String) {
    Text(
        modifier = Modifier.weight(1f),
        text = text,
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Center,
        style = caption02
    )
}

@ComponentPreviewBlack
@Composable
private fun HomeScreenTopBannerPageIndicatorTextPreview() {
    NanaLandTheme {
        Row {
            HomeScreenTopBannerPageIndicatorText(text = "1")
        }
    }
}