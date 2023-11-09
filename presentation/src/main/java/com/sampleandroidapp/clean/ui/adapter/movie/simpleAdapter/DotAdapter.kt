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
import javax.security.auth.callback.Callback

class DotsAdapter(
    private val totalDots: Int,
    private val currentPosition: (Int) -> Unit
)
: RecyclerView.Adapter<DotsAdapter.ViewHolder>() {

    private var _selectedPosition = 0

        set(value) {
            val oldPosition = field
            field = value
            notifyItemChanged(oldPosition)
            notifyItemChanged(value)
        }

    private val SELECTED_DOT_TYPE: Int = 1
    private val DEFAULT_DOT_TYPE: Int = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dot = View(parent.context)
        // The size doesn't need to be set here, as it will be set in onBindViewHolder
        val layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        dot.layoutParams = layoutParams
        dot.background = getDotDrawable()
        return ViewHolder(dot)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val size = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            if (position == _selectedPosition) 15f else 10f,
            context.resources.displayMetrics
        ).toInt()
        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.width = size
        layoutParams.height = size

        val defaultSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, context.resources.displayMetrics).toInt()
        val selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, context.resources.displayMetrics).toInt()
        val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, context.resources.displayMetrics).toInt()

        if (position == _selectedPosition) {
            val extraMargin = (selectedSize - defaultSize) / 2
            layoutParams.setMargins(margin, 0, margin, 0)
        } else {
            layoutParams.setMargins(margin, 0, margin, 0)
        }

        holder.itemView.layoutParams = layoutParams
        holder.itemView.isSelected = position == _selectedPosition
        currentPosition(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == _selectedPosition) SELECTED_DOT_TYPE else DEFAULT_DOT_TYPE
    }

    override fun getItemCount() = totalDots

    private fun getDotDrawable(): Drawable {
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