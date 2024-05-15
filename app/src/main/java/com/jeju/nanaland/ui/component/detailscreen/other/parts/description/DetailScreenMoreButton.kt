package com.jeju.nanaland.ui.component.detailscreen.other.parts.description

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun DetailScreenMoreButton(
    isMoreOpen: Boolean,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = if (isMoreOpen) "접기" else "더 보기",
        color = getColor().gray01,
        style = caption01
    )
}

@ComponentPreview
@Composable
private fun DetailScreenMoreButtonPreview() {
    NanaLandTheme {
        DetailScreenMoreButton(
            isMoreOpen = false,
            onClick = {}
        )
    }
}