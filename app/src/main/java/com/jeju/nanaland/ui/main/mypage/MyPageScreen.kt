package com.jeju.nanaland.ui.main.mypage

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.ui.component.common.TagChip2
import com.jeju.nanaland.ui.component.mypage.MyPageScreenBottomButton
import com.jeju.nanaland.ui.component.mypage.MyPageScreenDivider
import com.jeju.nanaland.ui.component.mypage.MyPageScreenIntroductionContent
import com.jeju.nanaland.ui.component.mypage.MyPageScreenIntroductionText
import com.jeju.nanaland.ui.component.mypage.MyPageScreenNickname
import com.jeju.nanaland.ui.component.mypage.MyPageScreenProfileImageWithLevel
import com.jeju.nanaland.ui.component.mypage.MyPageScreenTestAgainContent
import com.jeju.nanaland.ui.component.mypage.MyPageScreenTopBar
import com.jeju.nanaland.ui.component.mypage.MyPageScreenTravelType
import com.jeju.nanaland.ui.component.mypage.MyPageScreenTravelTypeText
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.UiState

@Composable
fun MyPageScreen(
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val userProfile = viewModel.userProfile.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getUserProfile()
    }
    MyPageScreen(
        userProfile = userProfile,
        moveToSettingsScreen = moveToSettingsScreen,
        moveToProfileModificationScreen = moveToProfileModificationScreen,
        isContent = true
    )
}

@Composable
private fun MyPageScreen(
    userProfile: UiState<UserProfile>,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    isContent: Boolean
) {
    val colorMain10 = getColor().main10
    val colorWhite = getColor().white
    val density = LocalDensity.current.density
    MyPageScreenTopBar {
        moveToSettingsScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    color = colorMain10
                )
                drawCircle(
                    color = colorWhite,
                    radius = 800f * density,
                    center = Offset(x = size.width / 2f, y = (800f + 84f) * density)
                )
            }
    ) {
        when (userProfile) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Spacer(Modifier.height(40.dp))

                MyPageScreenProfileImageWithLevel(
                    imageUri = userProfile.data.profileImageUrl ?: "",
                    level = userProfile.data.level ?: 1
                )

                Spacer(Modifier.height(8.dp))

                MyPageScreenNickname(nickname = userProfile.data.nickname ?: "")

                Spacer(Modifier.height(24.dp))

                MyPageScreenDivider()

                Spacer(Modifier.height(24.dp))

                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    MyPageScreenTravelTypeText()

                    Spacer(Modifier.height(16.dp))

                    MyPageScreenTravelType(text = userProfile.data.travelType ?: "")

                    Spacer(Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        userProfile.data.hashTags?.forEach {
                            TagChip2("#${it}")

                            Spacer(Modifier.width(8.dp))
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    MyPageScreenTestAgainContent {

                    }

                    Spacer(Modifier.height(32.dp))

                    MyPageScreenIntroductionText()

                    Spacer(Modifier.height(8.dp))

                    MyPageScreenIntroductionContent(text = userProfile.data.description ?: "")

                    Spacer(Modifier.weight(1f))
                }

                MyPageScreenBottomButton {
                    moveToProfileModificationScreen(
                        userProfile.data.profileImageUrl,
                        userProfile.data.nickname,
                        userProfile.data.description
                    )
                }

                Spacer(Modifier.height(20.dp))
            }
            is UiState.Failure -> {}
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {

}