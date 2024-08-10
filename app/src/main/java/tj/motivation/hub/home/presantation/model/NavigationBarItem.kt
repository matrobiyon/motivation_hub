package tj.motivation.hub.home.presantation.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector,
)
