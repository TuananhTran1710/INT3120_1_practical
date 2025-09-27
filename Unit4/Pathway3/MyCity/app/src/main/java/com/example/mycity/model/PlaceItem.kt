package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PlaceItem(
    @StringRes override val name: Int,
    @StringRes val address: Int,
    @StringRes val desc: Int,
    @DrawableRes override val img: Int,
) : ListItem(name, img)