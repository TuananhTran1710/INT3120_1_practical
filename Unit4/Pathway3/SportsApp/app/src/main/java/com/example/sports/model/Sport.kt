package com.example.sports.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

/**
 * Data model for Sport
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Sport(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val subtitleResourceId: Int,
    val playerCount: Int,
    val olympic: Boolean,
    @DrawableRes val imageResourceId: Int,
    @DrawableRes val sportsImageBanner: Int,
    @StringRes val sportDetails: Int,
    val calories: Int // <— thêm thuộc tính
) : Parcelable
