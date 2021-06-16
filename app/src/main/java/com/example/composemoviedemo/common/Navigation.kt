package com.example.composemoviedemo.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.composemoviedemo.R
import com.example.composemoviedemo.bill.BillScreen
import com.example.composemoviedemo.card.CardScreen
import com.example.composemoviedemo.common.model.Constants
import com.example.composemoviedemo.discount.view.DiscountScreen
import com.example.composemoviedemo.discount.viewModel.DiscountViewModel
import com.example.composemoviedemo.home.HomeScreen
import com.example.composemoviedemo.service.ServiceScreen

val items = listOf(
    Screen.CardScreen,
    Screen.ServiceScreen,
    Screen.HomeScreen,
    Screen.DiscountScreen,
    Screen.BillScreen
)

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    object CardScreen : Screen(Constants.ROUTE_CARD, Icons.Default.Search, R.string.tab_card)
    object ServiceScreen :
        Screen(Constants.ROUTE_SERVICE, Icons.Default.Store, R.string.tab_service)

    object HomeScreen :
        Screen(Constants.ROUTE_Home, Icons.Default.Favorite, R.string.tab_home)

    object DiscountScreen :
        Screen(Constants.ROUTE_DISCOUNT, Icons.Default.AccountCircle, R.string.tab_discount)

    object BillScreen :
        Screen(Constants.ROUTE_BILL, Icons.Default.Verified, R.string.tab_bill)
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Navigation() {
    val context = LocalContext.current.applicationContext
    val viewModel: DiscountViewModel = viewModel()
    val (_, setCanPop) = remember { mutableStateOf(false) }
    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }
    BuildScaffold(navController, viewModel, context)
}

@ExperimentalFoundationApi
@Composable
private fun BuildScaffold(
    navController: NavHostController,
    viewModel: DiscountViewModel,
    context: Context?
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.White) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, screen.route) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        },
                        selectedContentColor = Color(0xFF5BC5D2),

                        unselectedContentColor = Color(0xFF646464)
                    )
                }
            }
        }
    ) { innerPadding ->
        BuildBox(innerPadding, navController, viewModel, context)
    }
}

@ExperimentalFoundationApi
@Composable
private fun BuildBox(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: DiscountViewModel,
    context: Context?
) {
    Box(modifier = Modifier.padding(innerPadding)) {
        NavHost(navController, startDestination = Screen.DiscountScreen.route) {
            composable(Screen.CardScreen.route) {
                CardScreen()
            }
            composable(Screen.ServiceScreen.route) {
                ServiceScreen()
            }
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }
            composable(Screen.DiscountScreen.route) {
                DiscountScreen(
                    viewModel,
                    onClick = { photo ->
                        Toast.makeText(context, "$photo", Toast.LENGTH_SHORT).show()
                    }
                )
            }
            composable(Screen.BillScreen.route) {
                BillScreen()
            }
        }
    }
}