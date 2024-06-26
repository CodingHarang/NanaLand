package com.jeju.nanaland.ui.component.detailscreen.other.parts.information

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenInformationTitle(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02Bold
    )
}

@ComponentPreview
@Composable
private fun DetailScreenInformationTitlePreview() {
    NanaLandTheme {
        DetailScreenInformationTitle(text = "Title")
    }
}