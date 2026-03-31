package com.example.finalexamtest

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun InsertScreen(navController: NavHostController, viewModel: FoodViewModel = viewModel()) {

    var textFieldName by rememberSaveable { mutableStateOf("") }
    var textFieldPrice by rememberSaveable { mutableStateOf("") }
    var textFieldAmount by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Insert New Food", fontSize = 25.sp)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
            value = textFieldName,
            onValueChange = { textFieldName = it },
            label = { Text(text = "Food name") }
        )

//        Row(
//            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            RadioButton(selected = (selectedGender == "Male"), onClick = { selectedGender = "Male" })
//            Text("Male")
//            RadioButton(selected = (selectedGender == "Female"), onClick = { selectedGender = "Female" })
//            Text("Female")
//            RadioButton(selected = (selectedGender == "Other"), onClick = { selectedGender = "Other" })
//            Text("Other")
//        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
            value = textFieldPrice,
            onValueChange = { textFieldPrice = it },
            label = { Text(text = "Food Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
            value = textFieldAmount,
            onValueChange = { textFieldAmount = it },
            label = { Text(text = "Food Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.width(width = 130.dp),
                onClick = {
                    if ( textFieldName.isEmpty() || textFieldPrice.isEmpty() || textFieldAmount.isEmpty() ) {
                        Toast.makeText(context, "Please fill all inputs",
                            Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val newFood = Food(
                        name = textFieldName,
                        price = textFieldPrice.toInt(),
                        amount = textFieldAmount.toInt()
                    )

                    viewModel.insertFood(newFood) {
                        Toast.makeText(context, "Successfully Inserted",
                            Toast.LENGTH_SHORT).show()
                    }

                    navController.popBackStack()
                }
            ) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.width(width = 10.dp))
            Button(
                modifier = Modifier.width(width = 130.dp),
                onClick = {
                    navController.popBackStack()
                },
            ) {
                Text(text = "Cancel")
            }
        }
    }
}
