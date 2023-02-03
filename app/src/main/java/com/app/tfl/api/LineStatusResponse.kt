package com.app.tfl.api

data class LineStatusResponse(


    val name: String,


    val lineStatuses: List<LineStatuses>,
    val routeSections: List<String>,

)

data class LineStatuses(


    val statusSeverityDescription: String,
    val reason:String,



)


