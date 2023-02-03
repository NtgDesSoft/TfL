package com.app.tfl.api

import androidx.compose.ui.graphics.Color

data class LineStatusResponse(
    val name: String,
    val lineStatuses: List<LineStatuses>,
)

data class LineStatuses(
    val statusSeverityDescription: String,
    val reason: String,
)

val LineColorMap = HashMap<String, Color>(
    mapOf(
        "bakerloo" to Color(0xFF894E24),
        "central" to Color(0xFFDC241F),
        "circle" to Color(0xFFFFCE00),
        "district" to Color(0xFF007229),
        "hammersmith & city" to Color(0xFFD799AF),
        "metropolitan" to Color(0xFF751056),
        "northern" to Color(0xFF000000),
        "jubilee" to Color(0xFF697278),
        "piccadilly" to Color(0xFF001AA8),
        "victoria" to Color(0xFF01A0E2),
        "waterloo & city" to Color(0xFF76D0BD)
    )
)

