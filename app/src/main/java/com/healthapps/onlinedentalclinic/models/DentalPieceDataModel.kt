package com.healthapps.onlinedentalclinic.models

data class DentalPieceDataModel(
    var number: String,
    var description: String,
    var imageViewCode: Int?
) {
    constructor(): this(
        "",
        "",
        null
    )
}