package com.jeju.nanaland.ui.component.detailscreen.other.parts.notice

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenNoticeContent(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02
    )
}

@ComponentPreview
@Composable
private fun DetailScreenNoticeContentPreview() {
    NanaLandTheme {
        DetailScreenNoticeContent(text = "contents")
    }
}