package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.PlaceItem

object PlaceData {
    val defaultPlace = getCafeList()[0]

    fun getCafeList() = listOf(
        PlaceItem(
            R.string.kone_cafe,
            R.string.kone_cafe_addr,
            R.string.kone_cafe_desc,
            R.drawable.kone_cafe
        ),
        PlaceItem(
            R.string.giang_coffee,
            R.string.giang_coffee_addr,
            R.string.giang_coffee_desc,
            R.drawable.giang_coffee
        ),
        PlaceItem(
            R.string.lofita,
            R.string.lofita_addr,
            R.string.lofita_desc,
            R.drawable.lofita
        )
    )

    fun getRestaurantList() = listOf(
        PlaceItem(
            R.string.duong,
            R.string.duong_addr,
            R.string.duong_desc,
            R.drawable.duong
        ),
        PlaceItem(
            R.string.l_essence_de_cuisine,
            R.string.l_essence_de_cuisine_addr,
            R.string.l_essence_de_cuisine_desc,
            R.drawable.l_essence_de_cuisine
        ),
        PlaceItem(
            R.string.era,
            R.string.era_addr,
            R.string.era_desc,
            R.drawable.era
        )
    )

    fun getParkList() = listOf(
        PlaceItem(
            R.string.thu_le,
            R.string.thu_le_addr,
            R.string.thu_le_desc,
            R.drawable.thu_le_park
        ),
        PlaceItem(
            R.string.thong_nhat,
            R.string.thong_nhat_addr,
            R.string.thong_nhat_desc,
            R.drawable.thong_nhat
        ),
        PlaceItem(
            R.string.indira_gandhi,
            R.string.indira_gandhi_addr,
            R.string.indira_gandhi_desc,
            R.drawable.indira_gandhi
        )
    )

    fun getMallList() = listOf(
        PlaceItem(
            R.string.lotte,
            R.string.lotte_addr,
            R.string.lotte_desc,
            R.drawable.lotte
        ),
        PlaceItem(
            R.string.trang_tien,
            R.string.trang_tien_addr,
            R.string.trang_tien_desc,
            R.drawable.trang_tien
        ),
        PlaceItem(
            R.string.royal,
            R.string.royal_addr,
            R.string.royal_desc,
            R.drawable.royal
        )

    )
}