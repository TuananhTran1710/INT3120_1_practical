package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.CategoryItem
import com.example.mycity.model.CategoryType

object CategoryData {
    fun getCategoryList() = listOf(
        CategoryItem(
            type = CategoryType.Coffee,
            R.string.coffee,
            R.drawable.coffee
        ),
        CategoryItem(
            type = CategoryType.Restaurant,
            R.string.restaurants,
            R.drawable.restaurants
        ),
        CategoryItem(
            type = CategoryType.Park,
            R.string.parks,
            R.drawable.parks
        ),
        CategoryItem(
            type = CategoryType.Mall,
            R.string.mall,
            R.drawable.mall
        )
    )
}