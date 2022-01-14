package com.example.tecnobank.home.recyclerview

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnobank.R

class ListNotificationAdapter(
    private val clickedNotificationListener: ClickedNotificationListener,
    private val listNotification: List<NotificationItem>,
    private val positionViewPager: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listNotification.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NotificationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_notification, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NotificationViewHolder) {
            holder.data.text = listNotification[position].date
            holder.title.text = listNotification[position].title
            holder.text.text = listNotification[position].text
            holder.link.text = listNotification[position].link
            holder.cardNotification.setOnClickListener {
                clickedNotificationListener.clickNotificationListener(position)
            }
        }
    }

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardNotification: CardView = itemView.findViewById(R.id.card_notification)
        val data: TextView = itemView.findViewById(R.id.data_notification)
        val title: TextView = itemView.findViewById(R.id.title_notification)
        val text: TextView = itemView.findViewById(R.id.text_notification)
        val link: TextView = itemView.findViewById(R.id.link_notification)
    }

    interface ClickedNotificationListener {
        fun clickNotificationListener(positionRecyclerView: Int)
    }

    data class NotificationItem(val date: String,val title: String,val text: String,val link: String)

}
