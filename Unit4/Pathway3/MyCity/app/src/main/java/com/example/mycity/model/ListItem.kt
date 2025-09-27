package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class ListItem (
    @StringRes open val name: Int,
    @DrawableRes open val img: Int
)