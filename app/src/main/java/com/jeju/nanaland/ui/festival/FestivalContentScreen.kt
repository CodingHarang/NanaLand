package com.jeju.nanaland.ui.festival

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.festival.FestivalContentData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun FestivalContentScreen(
    contentId: Long?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Long, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: FestivalContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getFestivalContent(contentId, isSearch)
    }
    val festivalContent = viewModel.festivalContent.collectAsState().value
    FestivalContentScreen(
        festivalContent = festivalContent,
        toggleFavorite = viewModel::toggleFavorite,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalScreen = moveToInfoModificationProposalScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun FestivalContentScreen(
    festivalContent: UiState<FestivalContentData>,
    toggleFavorite: (Long, (Long, Boolean) -> Unit) -> Unit,
    updatePrevScreenListFavorite: (Long, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        CustomTopBar(
            title = "축제",
            onBackButtonClicked = moveToBackScreen
        )
        when (festivalContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.verticalScroll(scrollState)) {
                            DetailScreenTopBannerImage(imageUri = festivalContent.data.originUrl)

                            Spacer(Modifier.height(24.dp))

                            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                                DetailScreenDescription(
                                    isFavorite = festivalContent.data.favorite,
                                    tag = festivalContent.data.addressTag,
                                    title = festivalContent.data.title,
                                    content = festivalContent.data.content,
                                    onFavoriteButtonClicked = { toggleFavorite(festivalContent.data.id, updatePrevScreenListFavorite) },
                                    onShareButtonClicked = {},
                                    moveToSignInScreen = moveToSignInScreen,
                                )

                                Spacer(Modifier.height(24.dp))

                                if (!festivalContent.data.address.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_location_outlined,
                                        title = "주소",
                                        content = festivalContent.data.address
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }


                                if (!festivalContent.data.contact.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_phone_outlined,
                                        title = "연락처",
                                        content = festivalContent.data.contact
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!festivalContent.data.period.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_calendar_outlined,
                                        title = "기간",
                                        content = festivalContent.data.period
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!festivalContent.data.time.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_clock_outlined,
                                        title = "이용 시간",
                                        content = festivalContent.data.time
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!festivalContent.data.fee.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_ticket_outlined,
                                        title = "입장료",
                                        content = festivalContent.data.fee
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!festivalContent.data.homepage.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_clip_outlined,
                                        title = "홈페이지",
                                        content = festivalContent.data.homepage
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                Spacer(Modifier.height(16.dp))

                                DetailScreenInformationModificationProposalButton {
                                    moveToInfoModificationProposalScreen()
                                }
                            }

                            Spacer(Modifier.height(80.dp))
                        }

                    MoveToTopButton {
                        coroutineScope.launch { scrollState.animateScrollTo(0) }
                    }
                }
            }
            is UiState.Failure -> {}
        }
    }
}

@ScreenPreview
@Composable
private fun FestivalContentScreenPreview() {

}