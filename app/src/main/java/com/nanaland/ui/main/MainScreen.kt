package com.nanaland.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.nanaland.globalvalue.type.MainScreenViewType
import com.nanaland.ui.component.CustomSurface
import com.nanaland.ui.main.home.HomeScreen
import com.nanaland.ui.main.jejustory.JejuStoryScreen
import com.nanaland.ui.main.like.LikeScreen
import com.nanaland.ui.main.mynana.MyNanaScreen
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.caption02
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun MainScreen(
    moveToNanaPickListScreen: () -> Unit,
    moveToNanaPickContentScreen: (String) -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val viewType = viewModel.viewType.collectAsState().value
    val navigationItemContentList = viewModel.getNavigationItemContentList()
    MainScreen(
        viewType = viewType,
        navigationItemContentList = navigationItemContentList,
        updateViewType = viewModel::updateViewType,
        moveToNanaPickListScreen = moveToNanaPickListScreen,
        moveToNanaPickContentScreen = moveToNanaPickContentScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToExperienceScreen = moveToExperienceScreen,
        isContent = true
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainScreen(
    viewType: MainScreenViewType,
    navigationItemContentList: List<MainViewModel.NavigationItemContent>,
    updateViewType: (MainScreenViewType) -> Unit,
    moveToNanaPickListScreen: () -> Unit,
    moveToNanaPickContentScreen: (String) -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Scaffold(
            topBar = {},
            bottomBar = {
                MainNavigationBar(
                    viewType,
                    navigationItemContentList,
                    updateViewType = updateViewType
                )
            },
            floatingActionButton = {},
            containerColor = getColor().surface,
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            when (viewType) {
                MainScreenViewType.Home -> {
                    HomeScreen(
                        scaffoldPadding = it,
                        moveToNanaPickListScreen = moveToNanaPickListScreen,
                        moveToNatureListScreen = moveToNatureListScreen,
                        moveToFestivalListScreen = moveToFestivalListScreen,
                        moveToMarketListScreen = moveToMarketListScreen,
                        moveToExperienceScreen = moveToExperienceScreen,
                    )
                }
                MainScreenViewType.Like -> {
                    LikeScreen()
                }
                MainScreenViewType.JejuStory -> {
                    JejuStoryScreen()
                }
                MainScreenViewType.MyNana -> {
                    MyNanaScreen()
                }
            }
        }
    }
}

@Composable
fun MainNavigationBar(
    viewType: MainScreenViewType,
    navigationItemContentList: List<MainViewModel.NavigationItemContent>,
    updateViewType: (MainScreenViewType) -> Unit,
) {
    NavigationBar(
        modifier = Modifier
            .height(TOP_BAR_HEIGHT.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                shadowElevation = 20f
            },
        containerColor = Color(0xFFFFFFFF),
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        navigationItemContentList.forEachIndexed { _, navItem ->
            NavigationBarItem(
                modifier = Modifier.fillMaxHeight(),
                selected = viewType == navItem.viewType,
                onClick = { updateViewType(navItem.viewType) },
                icon = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = when (viewType == navItem.viewType) {
                                true -> navItem.iconSelected
                                false -> navItem.iconUnselected
                            }),
                            contentDescription = null,
                        )
                        Text(
                            text = navItem.label,
                            style = caption02
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = getColor().black,
                    selectedTextColor = getColor().black,
                    indicatorColor = Color(0x00FFFFFF),
                    unselectedIconColor = getColor().black25,
                    unselectedTextColor = getColor().black25
                ),
                interactionSource = NoRippleInteractionSource(),
                alwaysShowLabel = true
            )
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions = emptyFlow<Interaction>()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = false
}

@Preview
@Composable
private fun MainScreenPreview() {
    val navigationItemContentList = listOf(
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.Home,
            iconSelected = R.drawable.ic_home_filled,
            iconUnselected = R.drawable.ic_home_outlined,
            label = "홈"
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.Like,
            iconSelected = R.drawable.ic_heart_filled,
            iconUnselected = R.drawable.ic_heart_outlined,
            label = "찜"
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.JejuStory,
            iconSelected = R.drawable.ic_group_filled,
            iconUnselected = R.drawable.ic_group_outlined,
            label = "제주 이야기"
        ),
        MainViewModel.NavigationItemContent(
            viewType = MainScreenViewType.MyNana,
            iconSelected = R.drawable.ic_person_filled,
            iconUnselected = R.drawable.ic_person_outlined,
            label = "나의 나나"
        )
    )
    NanaLandTheme {
        MainNavigationBar(
            viewType = MainScreenViewType.MyNana,
            navigationItemContentList = navigationItemContentList,
            updateViewType = {}
        )
    }
}