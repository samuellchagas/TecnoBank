package com.example.tecnobank.home.recyclerview

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tecnobank.R

class DotsDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val indicatorHeight: Float
    private val indicatorItemPadding: Float
    private val radius: Float
    private val inactivePaint: Paint = Paint()
    private val activePaint: Paint = Paint()
    private val colorInactive: Int = Color.LTGRAY
    private val colorActive: Int = getColor(context,R.color.greenTecnoBank)

    init {
        val strokeWidth: Float = Resources.getSystem().displayMetrics.density * 1
        this.radius = Resources.getSystem().displayMetrics.density * 5
        this.indicatorHeight = Resources.getSystem().displayMetrics.density * 12

        inactivePaint.isAntiAlias = true
        inactivePaint.color = colorInactive
        activePaint.strokeCap = Paint.Cap.ROUND
        activePaint.strokeWidth = strokeWidth
        activePaint.style = Paint.Style.FILL
        activePaint.isAntiAlias = true
        activePaint.color = colorActive
        indicatorItemPadding = Resources.getSystem().displayMetrics.density * 8
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val adapter = parent.adapter ?: return
        val itemCount = adapter.itemCount

        val totalLength = (radius * 2 * itemCount)
        val paddingBetweenItems =
            (0.coerceAtLeast(itemCount - 1) * indicatorItemPadding)
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

        val indicatorPosY = parent.height /1.25f
        drawInactiveDots(c, indicatorStartX, indicatorPosY, itemCount)
        val activePosition: Int = (parent.layoutManager as LinearLayoutManager?)!!
            .findFirstCompletelyVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }
        drawActiveDot(c, indicatorStartX, indicatorPosY, activePosition)
    }

    private fun drawInactiveDots(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int
    ) {
        val itemWidth = (radius * 2 + indicatorItemPadding)
        var start = indicatorStartX + radius
        for (i in 0 until itemCount) {
            c.drawCircle(start, indicatorPosY, radius, inactivePaint)
            start += itemWidth
        }
    }

    private fun drawActiveDot(
        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
        highlightPosition: Int
    ) {
        val itemWidth = (radius * 2 + indicatorItemPadding)
        val highlightStart = indicatorStartX + radius + itemWidth * highlightPosition
        c.drawCircle(highlightStart, indicatorPosY, radius, activePaint)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = indicatorHeight.toInt()
    }
}