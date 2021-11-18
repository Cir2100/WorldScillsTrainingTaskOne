package com.kurilov.worldscillstrainingtaskone.data.classes

data class Name(
    val common : String,
    val official : String
)

data class Flags(
    val png : String,
    val svg : String
)

data class Country(
    val name : Name,
    val population : Int,
    val flags: Flags
)
