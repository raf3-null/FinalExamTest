package com.example.finalexamtest

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    var foodList = mutableStateListOf<Food>()
        private set

    fun getAllFood() {
        viewModelScope.launch {
            try {
                val response = FoodClient.foodAPI.retrieveFood()
                foodList.clear()
                foodList.addAll(response)

            } catch (e: Exception) {
                Log.e("FoodViewModel", "Get Error: ${e.message}")
            }
        }
    }

    fun insertFood(food: Food, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = FoodClient.foodAPI.insertFood(
                    id = food.id,
                    name = food.name,
                    price = food.price,
                    amount = food.amount
                )
                onSuccess()
                getAllFood()

            } catch (e: Exception) {
                Log.e("FoodViewModel", "Insert Error: ${e.message}")
            }
        }
    }
}