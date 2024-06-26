package com.jeju.nanaland.ui.component.detailscreen.other.parts.description

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenDescriptionContent(
    isMoreOpen: Boolean,
    text: String?
) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body01,
        maxLines = if (isMoreOpen) Int.MAX_VALUE else 4,
        overflow = TextOverflow.Ellipsis
    )
}

@ComponentPreview
@Composable
private fun DetailScreenDescriptionContentPreview1() {
    NanaLandTheme {
        DetailScreenDescriptionContent(
            isMoreOpen = true,
            text = "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription"
        )
    }
}

@ComponentPreview
@Composable
private fun DetailScreenDescriptionContentPreview2() {
    NanaLandTheme {
        DetailScreenDescriptionContent(
            isMoreOpen = false,
            text = "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription"
        )
    }
}