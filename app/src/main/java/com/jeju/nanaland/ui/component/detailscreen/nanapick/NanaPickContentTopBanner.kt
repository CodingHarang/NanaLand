package com.jeju.nanaland.ui.component.detailscreen.nanapick

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerSubTitle
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerTitle
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerFavoriteButton
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerShareButton
import com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner.NanaPickContentTopBannerVersion
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.ui.ScreenPreview
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickContentTopBanner(
    contentId: Long?,
    nanaPickContent: UiState.Success<NanaPickContentData>,
    toggleFavorite: () -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(getColor().skeleton)
    ) {
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            imageModel = { nanaPickContent.data.originUrl }
        )

        NanaPickContentTopBannerVersion(
            modifier = Modifier.align(Alignment.TopStart),
            text = ""
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 20.dp)
        ) {
            NanaPickContentTopBannerSubTitle(text = nanaPickContent.data.subHeading)

            NanaPickContentTopBannerTitle(text = nanaPickContent.data.heading)
        }

        NanaPickContentTopBannerShareButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 16.dp),
            onClick = {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "http://13.125.110.80:8080/share/${getLanguage()}?category=nanapick&id=${contentId}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }
        )

        NanaPickContentTopBannerFavoriteButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 56.dp),
            isFavorite = nanaPickContent.data.favorite,
            onClick = { toggleFavorite() },
            moveToSignInScreen = moveToSignInScreen,
        )
    }
}

@ScreenPreview
@Composable
private fun NanaPickContentTopBannerPreview() {

}