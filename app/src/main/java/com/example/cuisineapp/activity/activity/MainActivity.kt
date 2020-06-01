package com.example.cuisineapp.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cuisineapp.R
import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_row.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {

            var intent = Intent(this, RecipeList::class.java)
            var ingredients = IngredientsId.text.toString().trim()
            var searchTerm = recipeId.text.toString().trim()

            intent.putExtra("Ingredients", ingredients) //passing our editTexts data in the intent
            intent.putExtra("search", searchTerm)


            startActivity(Intent(intent))
        }
    }
}
