package com.haodanku.bianxian.entry

/**
 * Author      : Stuart
 * Date        : 2021/9/18 17:09
 * Description :
 */
data class CustomData(
    val code: Int,
    val msg: String? = null,
    val data: Inner? = null
)

data class Inner(
    val lists: List<Lists>? = null
)

data class Lists(
    val title: String? = null,
    val img: String? = null,
    val jump: String? = null,
    val items: List<ItemData>? = null
)

data class ItemData(
    val title: String?,
    val itempic: String?,
    val itemendprice: String?,
    val itemshorttitle: String?,
    val itemsale: String?,
    val shop_icon: String?
)