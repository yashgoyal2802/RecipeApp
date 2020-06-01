package com.example.cuisineapp.activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuisineapp.R
import com.example.cuisineapp.activity.data.RecipeListAdapter
import com.example.cuisineapp.activity.model.Recipe
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.example.cuisineapp.activity.model.LEFT_LINK
import com.example.cuisineapp.activity.model.QUERY
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject
import kotlin.Exception

class RecipeList : AppCompatActivity() {
    //in this class we will set up our volley
    var volleyRequest: RequestQueue? = null
    var recipeList: ArrayList<Recipe>? = null
    var recipeAdapter: RecipeListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        //creating url string variable
        var urlString = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"

        var url: String?

        var extras = intent.extras          ///putting up intent data from put extras into object
        var ingredients = extras.get("Ingredients")
        var searchTerm =  extras.get("search")
        if(extras != null && !ingredients.equals("")              // after this information we need to construct our url
            && !searchTerm.equals("")) {

            //constructing the url
            var tempUrl = LEFT_LINK + ingredients + QUERY + searchTerm    //format of specific url

            url = tempUrl
        }
        else{

            url = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"

        }





        //instantiating our recipeList

        recipeList = ArrayList<Recipe>()
        volleyRequest = Volley.newRequestQueue(this)

        getRecipe(url)

    }


    fun getRecipe(url: String){

        val recipeRequest = JsonObjectRequest(Request.Method.GET,
            url, Response.Listener{
                    response: JSONObject ->   //watch it on the url what type needs to be passed
                // response here is the whole json code on url
                try {

                    val resultArray = response.getJSONArray("results")
                    for (i in 0..resultArray.length()-1){ //we had a json object inside the array on url site
                        // hence we need to create an object

                        var recipeObj = resultArray.getJSONObject(i)

                        var title = recipeObj.getString("title")
                        var link = recipeObj.getString("href")
                        var thumbnail = recipeObj.getString("thumbnail")
                        var ingredients = recipeObj.getString("ingredients")

                        Log.d("CHUAGDASKBDJGIW", title)

                        //now we construct our recipe object
                        var recipe = Recipe(title, link, ingredients, thumbnail)    // this object is now to be passed in arrayList which we have to create
                        recipe.title = title
                        recipe.link = link
                        recipe.thumbnail = thumbnail
                        recipe.ingredients = "Ingredients: ${ingredients}"


                        recipeList!!.add(recipe)

                        layoutManager = LinearLayoutManager(this)

                        recipeAdapter = RecipeListAdapter(recipeList!!,this)


                        //setup list/recyclerview
                        recyclerViewID.layoutManager = layoutManager
                        recyclerViewID.adapter = recipeAdapter

                    }

                    recipeAdapter!!.notifyDataSetChanged()                       //important as it allow the adapter to connect with recycler view
                    // and conduct all changes to be done inorder for us to see what shows up in our listView


                }

                catch (e: Exception){e.printStackTrace()}

            }, Response.ErrorListener {
                    error: VolleyError? ->
                try{
                    Log.d("chuagdusd", "sjabdsd")
                }

                catch (e: JSONException){e.printStackTrace()}
            })


        volleyRequest!!.add(recipeRequest)
    }
}
