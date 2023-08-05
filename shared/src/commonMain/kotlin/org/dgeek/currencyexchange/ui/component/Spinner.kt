package org.dgeek.currencyexchange.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun CustomDropdown(
    availableCurrency: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var dropdownHeight by remember { mutableStateOf(0.dp) }

    BoxWithConstraints {
        Text(
            availableCurrency[selectedIndex],
            modifier = Modifier.clickable { expanded = !expanded },
            color = Color.Black
        )

        dropdownHeight = 20.dp

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = dropdownHeight)
                .shadow(4.dp)
                .zIndex(if (expanded) 20f else 0f)
                .background(Color.Red)
        ) {
            if (expanded) {
                LazyColumn(modifier = Modifier.background(Color.LightGray).height(100.dp)) {
                    items(availableCurrency.size) { key ->
                        Text(
                            text = availableCurrency[key],
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedIndex = key
                                    onItemSelected(availableCurrency[key])
                                    expanded = false
                                }
                                .padding(10.dp),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

