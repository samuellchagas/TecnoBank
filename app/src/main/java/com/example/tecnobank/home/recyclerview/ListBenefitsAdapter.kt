package com.example.tecnobank.home.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnobank.R
import com.example.tecnobank.data.remote.model.home.BalanceBenefitsResponse
import com.squareup.picasso.Picasso

class ListBenefitsAdapter(
    private val listBenefitsResponse: List<BalanceBenefitsResponse.Benefits>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listBenefitsResponse.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.benefits_cardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostViewHolder) {
            holder.banner.setBackgroundColor(Color.parseColor(listBenefitsResponse[position]
                .indicatorColor))
            Picasso.with(holder.image.context).load(listBenefitsResponse[position].image)
                .into(holder.image)
            holder.title.text = listBenefitsResponse[position].title
            holder.description.text = listBenefitsResponse[position].message
            holder.link.text = listBenefitsResponse[position].textLink
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val banner: View = itemView.findViewById(R.id.banner_colorful)
        val image: ImageView = itemView.findViewById(R.id.image_benefits)
        val title: TextView = itemView.findViewById(R.id.title_benefits)
        val description: TextView = itemView.findViewById(R.id.description_benefits)
        val link: TextView = itemView.findViewById(R.id.require_card)
    }

}
