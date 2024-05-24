package com.madona.chapter_two.app_three

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShoppingCartScreen() {
    val viewModel: CartViewModel = viewModel()
    val products by viewModel.products.collectAsState()
    val cart by viewModel.cart.collectAsState()

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        ProductList(products, viewModel::addToCart)
        Spacer(modifier = Modifier.height(16.dp))
        CartSummary(cart, viewModel::removeFromCart)
    }
}

@Composable
fun ProductList(products: List<Product>, onAddToCart: (Product) -> Unit) {
    LazyColumn {
        items(products) { product ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = product.name)
                Button(onClick = { onAddToCart(product) }) {
                    Text("Add to Cart")
                }
            }
        }
    }
}

@Composable
fun CartSummary(cart: Map<Product, Int>, onRemoveFromCart: (Product) -> Unit) {
    Column {
        Text("Cart Summary", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        cart.forEach { (product, quantity) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${product.name} x $quantity")
                Button(onClick = { onRemoveFromCart(product) }) {
                    Text("Remove")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val totalPrice = cart.entries.sumOf { (product, quantity) -> product.price * quantity }
        Text("Total Price: $totalPrice", style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowShoppingCartApp(){
    ShoppingCartScreen()
}