package com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreviewBlack
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun NanaPickContentTopBannerShareButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(R.drawable.ic_share_outlined),
        contentDescription = null,
        colorFilter = ColorFilter.tint(getColor().white)
    )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerShareButtonPreview() {
    NanaLandTheme {
        NanaPickContentTopBannerShareButton {

        }
    }
}