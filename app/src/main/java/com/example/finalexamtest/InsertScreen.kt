package com.example.finalexamtest

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(navController: NavHostController, viewModel: ShirtViewModel = viewModel()) {
    var customerName by rememberSaveable { mutableStateOf("") }
    
    // Shirt Types
    val shirtTypes = listOf("Tank top (100)", "T-shirt (180)", "Sweater (250)")
    val shirtTypePrices = mapOf("Tank top (100)" to 100, "T-shirt (180)" to 180, "Sweater (250)" to 250)
    var expanded by remember { mutableStateOf(false) }
    var selectedType by rememberSaveable { mutableStateOf(shirtTypes[0]) }

    // Shirt Sizes
    val shirtSizes = listOf("S (+50)", "M (+80)", "L (+250)")
    val shirtSizePrices = mapOf("S (+50)" to 50, "M (+80)" to 80, "L (+250)" to 250)
    var selectedSize by rememberSaveable { mutableStateOf(shirtSizes[0]) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Add New Order",
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Customer Name
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text(text = "Customer name") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Shirt Type Dropdown
        Text(text = "Shirt Type:", fontSize = 18.sp)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, true).fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                shirtTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(text = type) },
                        onClick = {
                            selectedType = type
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Shirt Size Radio Buttons
        Text(text = "Shirt Size:", fontSize = 18.sp)
        shirtSizes.forEach { size ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = (selectedSize == size),
                    onClick = { selectedSize = size }
                )
                Text(text = size, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (customerName.isEmpty()) {
                        Toast.makeText(context, "Please enter customer name", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val typePrice = shirtTypePrices[selectedType] ?: 0
                    val sizePrice = shirtSizePrices[selectedSize] ?: 0
                    val totalPrice = typePrice + sizePrice

                    val newOrder = Shirt(
                        name = customerName,
                        type = selectedType,
                        size = selectedSize,
                        price = totalPrice
                    )

                    viewModel.insertFood(newOrder) {
                        Toast.makeText(context, "Order Saved", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }
            ) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(text = "Cancel")
            }
        }
    }
}
