package com.example.tecnobank.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnobank.R

class ListServicesAdapter(
    private val listServices: List<ItemService>,
    private val clickedServiceListener: ClickedServiceListener,
    private val positionViewPager: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return listServices.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardServicesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.services_cardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CardServicesViewHolder) {
            if(listServices[position].incompletService) {
                holder.cardButton.setCardBackgroundColor(
                    getColor(
                        holder.cardButton.context,
                        R.color.gray_200
                    )
                )
                holder.icon.setImageResource(listServices[position].icon)
                holder.icon.drawable.setTint(
                    getColor(
                        holder.cardButton.context,
                        R.color.gray_700
                    )
                )
            } else {
                holder.icon.setImageResource(listServices[position].icon)
            }
            if (!(listServices[position].titleInfo.isNullOrEmpty())) {
                holder.cardDecor.isVisible = true
                holder.textDecor.text = listServices[position].titleInfo
            } else {
                holder.cardDecor.isVisible = false
                holder.cardDecor.setCardBackgroundColor(
                    getColor(
                        holder.cardButton.context,
                        R.color.gray_500
                    )
                )
            }
            holder.title.text = listServices[position].title
            holder.cardButton.setOnClickListener {
                clickedServiceListener.clickServiceListener(position, positionViewPager)
            }
        }
    }

    class CardServicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardDecor: CardView = itemView.findViewById(R.id.card_decor)
        val textDecor: TextView = itemView.findViewById(R.id.text_decor)
        val icon: ImageView = itemView.findViewById(R.id.icon_services)
        val title: TextView = itemView.findViewById(R.id.title_service)
        val cardButton: CardView = itemView.findViewById(R.id.services_cardview)
    }

    data class ItemService(
        val incompletService: Boolean,
        val titleInfo: String?,
        val title: String,
        val icon: Int
    )

    interface ClickedServiceListener {
        fun clickServiceListener(positionRecyclerView: Int, positionViewPager: Int)
    }
}
