package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_INITIALIZATION
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.constant.ROUTE_WITHDRAW
import com.jeju.nanaland.ui.withdrawal.WithdrawalScreen

fun NavGraphBuilder.withdrawalScreen(navController: NavController) = composable(route = ROUTE_WITHDRAW) {
    WithdrawalScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToLanguageInitScreen = { navController.navigate(ROUTE_LANGUAGE_INITIALIZATION) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } }
    )
}