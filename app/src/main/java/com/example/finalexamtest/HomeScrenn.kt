package com.example.finalexamtest

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: FoodViewModel = viewModel()
) {
    val total = viewModel.foodList.sumOf { it.price * it.amount }
    val vat = total * 0.07
    val grandTotal = total + vat

    val contextForToast = LocalContext.current.applicationContext
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.getAllFood()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
    Column {
        Spacer(modifier = Modifier.height(height = 20.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(weight = 0.85f)) {
                Text(
                    text = "Food Lists: ${viewModel.foodList.size}",
                    fontSize = 25.sp
                )

                Text(
                    text = "Total: $total",
                    fontSize = 18.sp
                )

                Text(
                    text = "VAT 7%%: %.2f".format(vat),
                    fontSize = 18.sp
                )

                Text(
                    text = "Grand Total: %.2f".format(grandTotal),
                    fontSize = 20.sp,
                    color = Color.Red
                )
            }
            Button(onClick = {
                navController.navigate(Screen.Insert.route)
            }) {
                Text(text = "Add Food")
            }
        }
        LazyColumn (
                verticalArrangement = Arrangement.spacedBy(space = 6.dp)
                ) {
            items(
                items = viewModel.foodList,
                key = { it.id }
            ) { item ->
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(size = 16.dp)),
                    onClick = {
                        Toast.makeText(
                            contextForToast, "Click on ${item.name}.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(weight = 0.85f),
                            text = "ID: ${item.id}\n" +
                                    "Name: ${item.name}\n" +
                                    "Price: ${item.price}\n" +
                                    "Amount: ${item.amount}\n" +
                                    "Subtotal: ${item.price * item.amount} ",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}
