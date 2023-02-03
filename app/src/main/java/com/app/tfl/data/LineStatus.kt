package com.app.tfl.data

import androidx.compose.ui.graphics.Color

data class LineStatus(
    val name:String,
    val colour:Color,
    val statuses:Statuses
)

data class Statuses(
    val Severity: String?,
    val reason:String?,
)
