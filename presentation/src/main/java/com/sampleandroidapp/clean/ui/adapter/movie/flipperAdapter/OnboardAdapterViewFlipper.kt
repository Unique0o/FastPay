package com.sampleandroidapp.clean.ui.adapter.movie.flipperAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sampleandroidapp.clean.R
import com.sampleandroidapp.clean.entities.OnboardModel

class OnboardAdapterViewFlipper(private val context: Context, private val items: List<OnboardModel>) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position) as OnboardModel
        // Inflate your custom layout
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.onboard_place_holder_view, parent, false)
        // Initialize your views and set data
         view.findViewById<ImageView>(R.id.imageView).setImageDrawable(ContextCompat.getDrawable(context, item.image))
         view.findViewById<TextView>(R.id.tvTitle).text = item.title
         view.findViewById<TextView>(R.id.tvDescription).text = item.description
        return view
    }
}