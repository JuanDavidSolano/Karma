package com.example.karma.model

data class Favor(val title:String = "DEFAULT TITLE",
                 val description:String="DEFAULT NAME",
                 val karma:Int=0,
                 val user: String="DEFAULT USER",
                 val doer: String="DEFAULT DOER",
                 val status: String="Sin asignar")