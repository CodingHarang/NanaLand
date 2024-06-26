package com.jeju.nanaland.ui.component.listscreen.filter.parts.location

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun LocationFilterDialogTitle() {
    Text(
        text = getString(R.string.location_filter_dialog_지역),
        color = getColor().black,
        style = title02Bold
    )
}

@ComponentPreview
@Composable
private fun LocationFilterDialogTitlePreview() {
    NanaLandTheme {
        LocationFilterDialogTitle()
    }
}