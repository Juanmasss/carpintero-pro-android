package com.juanma.carpinteropro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.juanma.carpinteropro.ui.navigation.BottomNavItem
import com.juanma.carpinteropro.ui.screens.CutsScreen
import com.juanma.carpinteropro.ui.screens.QuotesScreen
import com.juanma.carpinteropro.ui.screens.ToolsScreen
import com.juanma.carpinteropro.ui.screens.WoodsScreen
import com.juanma.carpinteropro.ui.theme.CarpinteroProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarpinteroProTheme {
                CarpenterApp()
            }
        }
    }
}

@Composable
fun CarpenterApp() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Woods,
        BottomNavItem.Tools,
        BottomNavItem.Quotes,
        BottomNavItem.Cuts
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Woods.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Woods.route) { WoodsScreen() }
            composable(BottomNavItem.Tools.route) { ToolsScreen() }
            composable(BottomNavItem.Quotes.route) { QuotesScreen() }
            composable(BottomNavItem.Cuts.route) { CutsScreen() }
        }
    }
}