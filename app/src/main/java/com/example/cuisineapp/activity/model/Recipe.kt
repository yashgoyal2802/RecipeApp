package com.example.cuisineapp.activity.model

class Recipe {
    var title: String? = null
    var link: String? = null
    var ingredients: String? = null
    var thumbnail: String? = null

    constructor(
        title: String, link: String,
        ingredients: String, thumbnail: String): super() {
        this.title = title
        this.link = link
        this.ingredients = ingredients
        this.thumbnail = thumbnail
    }
}