package com.sampleandroidapp.clean.ui.adapter.generic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T>(
    private val layoutId: Int,
    private val bind: (item: T, view: View) -> Unit,
    private val itemClick: ((item: T) -> Unit)? = null,
    itemsSame: (oldItem: T, newItem: T) -> Boolean,
    contentsSame: (oldItem: T, newItem: T) -> Boolean
) : ListAdapter<T, GenericAdapter.GenericViewHolder<T>>(GenericDiffUtil(itemsSame, contentsSame)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GenericViewHolder(itemView, bind, itemClick)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    class GenericViewHolder<T>(
        itemView: View,
        private val bind: (item: T, view: View) -> Unit,
        private val itemClick: ((item: T) -> Unit)?
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: T) {
            bind(item, itemView)
            itemClick?.let { click -> itemView.setOnClickListener { click(item) } }
        }
    }

    class GenericDiffUtil<T>(
        private val itemsSame: (oldItem: T, newItem: T) -> Boolean,
        private val contentsSame: (oldItem: T, newItem: T) -> Boolean
    ) : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean = itemsSame(oldItem, newItem)

        override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean = contentsSame(oldItem, newItem)
    }
}