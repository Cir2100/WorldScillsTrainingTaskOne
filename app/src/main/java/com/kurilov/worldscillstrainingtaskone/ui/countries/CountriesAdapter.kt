package com.kurilov.worldscillstrainingtaskone.ui.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kurilov.worldscillstrainingtaskone.R
import com.kurilov.worldscillstrainingtaskone.data.classes.Country
import com.squareup.picasso.Picasso

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.MyViewHolder>() {

    private var items : List<Country> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.countryNameTextView?.text = item.name.common
        holder.countryPopulationTextView?.text = "Популяция: ${item.population}"
        Picasso.get()
            .load(item.flags.png)
            //.error(R.drawable.ball_image)
            .into(holder.countryFlagImageView)

    }

    fun updateItems(items: List<Country>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var countryNameTextView: TextView? = null
        var countryPopulationTextView: TextView? = null
        var countryFlagImageView: ImageView? = null

        init {

            countryNameTextView = itemView.findViewById(R.id.country_name_textView)
            countryPopulationTextView = itemView.findViewById(R.id.country_population_textView)
            countryFlagImageView = itemView.findViewById(R.id.country_flag_imageView)

        }
    }

    override fun getItemCount() = items.size
}