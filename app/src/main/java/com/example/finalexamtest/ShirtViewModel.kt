package com.example.finalexamtest

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ShirtViewModel : ViewModel() {
    var shirtList = mutableStateListOf<Shirt>()
        private set

    fun getAllFood() {
        viewModelScope.launch {
            try {
                val response = ShirtClient.shirtAPI.retrieveFood()
                shirtList.clear()
                shirtList.addAll(response)
            } catch (e: Exception) {
                Log.e("FoodViewModel", "Get Error: ${e.message}")
            }
        }
    }

    fun insertFood(shirt: Shirt, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                ShirtClient.shirtAPI.insertFood(
                    name = shirt.name,
                    type = shirt.type,
                    size = shirt.size,
                    price = shirt.price
                )
                onSuccess()
                getAllFood()
            } catch (e: Exception) {
                Log.e("FoodViewModel", "Insert Error: ${e.message}")
            }
        }
    }

    fun deleteFood(id: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = ShirtClient.shirtAPI.deleteFood(id)
                if (response.isSuccessful) {
                    onSuccess()
                    getAllFood()
                }
            } catch (e: Exception) {
                Log.e("FoodViewModel", "Delete Error: ${e.message}")
            }
        }
    }
}