package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun JejuMapNoticeText() {
    Text(
        modifier = Modifier
            .padding(end = 16.dp)
            .fillMaxWidth(),
        text = "*참고용 사진입니다.",
        color = getColor().gray01,
        textAlign = TextAlign.End,
        style = caption01
    )
}