package com.example.cuisineapp.activity.data
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineapp.R
import com.example.cuisineapp.ShowLinkActivity
import com.example.cuisineapp.activity.model.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter(private val list: ArrayList<Recipe>,
                        private val context: Context) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        // here we inflate our listRow to make it actual kotlin object which we pass in viewHolder class

        val view = LayoutInflater.from(context)  //gets the text xml file and makes it an object
            .inflate(R.layout.list_row, parent, false)

        return ViewHolder(view)     // ViewHolder class is invoked created below
        // it inherits viewHolder hence returns a viewHolder which we created above
    }

    override fun getItemCount(): Int {
        // keeps a count of how many objects are there in our list
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(list[position])  //recipe object is in list
    }

    // viewHolder here is inheriting recycler view hence the recyclerView<adapter.viewHolder> is working
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        // THIS class will fetch all the widgets we have in listRow
        // the itemView is view type which above keeps track of all widgets in list row
        //angular brackets specify which type of view it has bcz view doesn't specifies

        var title = itemView.findViewById<TextView>(R.id.recipeTile)
        var ingredients = itemView.findViewById<TextView>(R.id.recipeIngredients)
        var thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
        var link = itemView.findViewById<Button>(R.id.linkButton)

        fun bindView(recipe: Recipe){
            title.text = recipe.title
            ingredients.text = recipe.ingredients


            //to parse thumbnail from web we use picasso library
            if(!TextUtils.isEmpty(recipe.thumbnail)){
                Picasso.get().load(recipe.thumbnail)  //this is the link to imageView
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(thumbnail) //this is an imageView
            }
            else{
                Picasso.get().load(R.mipmap.oops).into(thumbnail)

            }

            link.setOnClickListener {

                var intent = Intent(context, ShowLinkActivity::class.java)
                intent.putExtra("link", recipe.link.toString()) // link here is href available in the url api
                context.startActivity(intent)      // inside adapter you need to start activity using context

            }

        }

    }
}