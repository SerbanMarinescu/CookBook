package com.example.cookbook.navigation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cookbook.navigation.util.MenuItem

@Composable
fun NotchedBottomBar(
    fabSize: Dp,
    menuOptions: List<MenuItem>,
    selectedIndex: Int,
    itemSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(
                WindowInsets.navigationBars.only(
                    sides = WindowInsetsSides.Bottom
                )
            )
    ) {
        Canvas(
            modifier = Modifier.matchParentSize()
        ) {
            val fabSizePx = fabSize.toPx()

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(
                    x = size.width / 2f - fabSizePx / 2f - 30f,
                    y = 0f
                )
                cubicTo(
                    x1 = size.width / 2f - fabSizePx / 2f,
                    y1 = size.height / 2f + fabSizePx / 5f,
                    x2 = size.width / 2f + fabSizePx / 2f,
                    y2 = size.height / 2f + fabSizePx / 5f,
                    x3 = size.width / 2f + fabSizePx / 2f + 30f,
                    y3 = 0f
                )
                lineTo(
                    x = size.width,
                    y = 0f
                )
            }

            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 2f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            menuOptions.forEachIndexed { index, menuItem ->
                BottomMenuItem(
                    icon = if(selectedIndex == index) menuItem.selectedIcon else menuItem.unselectedIcon,
                    text = menuItem.label,
                    onClick = {
                        itemSelected(index)
                    }
                )
                if(index == 0 || index == 2) {
                    Spacer(Modifier.width(8.dp))
                }
                if(index == 1) {
                    Spacer(Modifier.width(50.dp))
                }
            }
        }
    }
}