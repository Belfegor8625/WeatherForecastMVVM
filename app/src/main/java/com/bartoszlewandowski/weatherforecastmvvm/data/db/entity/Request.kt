package com.bartoszlewandowski.weatherforecastmvvm.data.db.entity


data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)