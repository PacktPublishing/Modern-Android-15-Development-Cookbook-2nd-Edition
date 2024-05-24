package com.madona.chapter_two.app_three

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class Product(val id: Int, val name: String, val price: Double)

class CartViewModel : ViewModel() {
    private val _products = MutableStateFlow(listOf<Product>())
    private val _cart = MutableStateFlow(mutableMapOf<Product, Int>())

    val products: StateFlow<List<Product>> = _products
    val cart: StateFlow<Map<Product, Int>> = _cart

    init {
        loadProducts()
    }

    private fun loadProducts() {
        // Simulate loading products from a repository or API
        _products.value = listOf(
            Product(1, "Product 1", 10.0),
            Product(2, "Product 2", 20.0),
            Product(3, "Product 3", 30.0)
        )
    }

    fun addToCart(product: Product) {
        _cart.update { currentCart ->
            val newCart = currentCart.toMutableMap()
            newCart[product] = (newCart[product] ?: 0) + 1
            newCart
        }
    }

    fun removeFromCart(product: Product) {
        _cart.update { currentCart ->
            val newCart = currentCart.toMutableMap()
            val currentQuantity = newCart[product]
            if (currentQuantity != null) {
                if (currentQuantity > 1) {
                    newCart[product] = currentQuantity - 1
                } else {
                    newCart.remove(product)
                }
            }
            newCart
        }
    }
}
