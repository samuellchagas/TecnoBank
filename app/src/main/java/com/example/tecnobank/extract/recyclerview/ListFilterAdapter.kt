package com.example.tecnobank.extract.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnobank.R

class ListFilterAdapter(
    private val listItemFilter: List<String>,
    private val selectFilterlistener: SelectFilterlistener,
    private var positionSelected: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilterItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FilterItemViewHolder) {
            holder.bind(listItemFilter,positionSelected)
            holder.itemView.setOnClickListener {
                positionSelected = position
                notifyDataSetChanged()
                selectFilterlistener.selectedFilterlistener(positionSelected)
            }

        }
    }

    class FilterItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val description: TextView = itemView.findViewById(R.id.description_filter)
        private val icon: ImageView = itemView.findViewById(R.id.image_check)

        fun bind(listItemFilter: List<String>, positionSelected: Int) {
            description.text = listItemFilter[position]
            if (position == positionSelected) {
                icon.setImageResource(R.drawable.ic_check)
            } else {
                icon.setImageResource(0)
            }
        }
    }

    interface SelectFilterlistener {
        fun selectedFilterlistener(position: Int)
    }

}
