package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class CategoryType {
    Coffee,
    Restaurant,
    Park,
    Mall
}

data class CategoryItem (
    val type: CategoryType,
    @StringRes override val name: Int,
    @DrawableRes override val img: Int
) : ListItem(name, img)