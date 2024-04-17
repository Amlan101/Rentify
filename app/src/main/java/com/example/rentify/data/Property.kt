package com.example.rentify.data

data class Property(
    val propertyName: String,
    val type: String,
    val listingDate: String,
    val location: String,
    val rating: Double,
    val price: Int
)