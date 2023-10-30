package com.sampleandroidapp.clean.ui.adapter.movie.simpleAdapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DotsAdapter(private val totalDots: Int) : RecyclerView.Adapter<DotsAdapter.ViewHolder>() {

    private var _selectedPosition = 0
        set(value) {
            val oldPosition = field
            field = value
            notifyItemChanged(oldPosition)
            notifyItemChanged(value)
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create dot view programmatically
        val dot = View(parent.context)
        val size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, parent.context.resources.displayMetrics).toInt()
        val layoutParams = RecyclerView.LayoutParams(size, size)
        val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, parent.context.resources.displayMetrics).toInt()
        layoutParams.setMargins(margin, 0, margin, 0)
        dot.layoutParams = layoutParams

        // You can set background color or other properties for the dot here
        dot.background = getDotDrawable(parent.context)

        return ViewHolder(dot)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Change dot appearance based on whether it's selected
        holder.itemView.isSelected = position == _selectedPosition
    }

    override fun getItemCount() = totalDots

    private fun getDotDrawable(context: Context): Drawable {
        // Programmatically create a state list drawable for the dot
        val stateListDrawable = StateListDrawable()
        val selectedDot = ShapeDrawable(OvalShape())
        selectedDot.paint.color = Color.BLUE
        val defaultDot = ShapeDrawable(OvalShape())
        defaultDot.paint.color = Color.GRAY

        stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), selectedDot)
        stateListDrawable.addState(intArrayOf(), defaultDot)

        return stateListDrawable
    }
}