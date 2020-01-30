package com.example.androidtask.Products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtask.R

/**
 */
class RecyclerViewAdapter(
    val list: ArrayList<DataHolder>,
    var activity: Context?
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(
            v
        )
    }


    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: DataHolder) {
            val context = itemView.context
            val textView_Title: TextView = itemView.findViewById(R.id.text_title)
            val textView_tag: TextView = itemView.findViewById(R.id.text_tag)
            val image: ImageView = itemView.findViewById(R.id.imageView)
            textView_Title.text = data.Title
            textView_tag.text = data.Tag

            Glide.with(context)
                .load(data.Image)
                .placeholder(R.drawable.image)
                .into(image)


        }

    }
}