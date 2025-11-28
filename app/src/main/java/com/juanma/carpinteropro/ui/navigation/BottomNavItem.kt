package com.juanma.carpinteropro.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Woods : BottomNavItem("woods", "Maderas", Icons.Filled.Home)
    object Tools : BottomNavItem("tools", "Herramientas", Icons.Filled.Build)
    object Quotes : BottomNavItem("quotes", "Cotizar", Icons.Filled.Edit)
    object Cuts : BottomNavItem("cuts", "Cortes", Icons.Filled.ContentCut)
}
