package com.example.tecnobank.home.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagerDecoratorDots : RecyclerView.ItemDecoration() {

    private var paintStroke: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 4f
        color = Color.BLACK
    }

    private val paintFill = Paint().apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#5cbd4c")
    }

    private val indicators = mutableListOf<Pair<Float, Float>>()
    private val indicatorRadius = 20f
    private val indicatorPadding = 180f

    private var activeIndicator = 0
    private var isInitialized = false

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (!isInitialized) {
            setupIndicators(parent)
        }

        parent.adapter?.let {
            with(canvas) {
                drawCircle(indicators[0].first, indicators[0].second)
                drawCircle(indicators[1].first, indicators[1].second)
                drawCircle(indicators[2].first, indicators[2].second)
                drawCircle(indicators[3].first, indicators[3].second)
            }

            val visibleItem = (parent.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()

            if(visibleItem >= 0) {
                activeIndicator = visibleItem
            }

            when (activeIndicator) {
                0 -> canvas.drawCircle(indicators[0].first, indicators[0].second, true)
                1 -> canvas.drawCircle(indicators[1].first, indicators[1].second, true)
                2 -> canvas.drawCircle(indicators[2].first, indicators[2].second, true)
                3 -> canvas.drawCircle(indicators[3].first, indicators[3].second, true)
            }
        }
    }

    private fun Canvas.drawCircle(x: Float, y: Float, isFill: Boolean = false) {
        drawCircle(x, y, indicatorRadius, if(isFill) paintFill else paintStroke)
    }

    private fun setupIndicators(recyclerView: RecyclerView) {
        isInitialized = true

        val indicatorTotalWidth = indicatorRadius * 6 + indicatorPadding
        val indicatorPosX = (((recyclerView.width - indicatorTotalWidth)) + 120f) / 2f
        val indicatorPosY = recyclerView.height - (indicatorRadius * 6 / 2f)

        indicators.add(indicatorPosX to indicatorPosY)
        indicators.add((indicatorPosX + indicatorRadius * 3) to indicatorPosY)
        indicators.add((indicatorPosX + indicatorRadius * 6) to indicatorPosY)
        indicators.add((indicatorPosX + indicatorRadius * 9) to indicatorPosY)
    }

    private fun checkIfIndicatorPressing(touchX: Float, touchY: Float): Int?{
        indicators.indices.forEach {
            if(Math.sqrt(Math.pow(((indicators[it].first - touchX).toDouble()), 2.0)
                        + Math.pow(((indicators[it].second - touchY).toDouble()), 2.0))
                <= indicatorRadius * 2) {
                return it
            }
        }
        return null
    }

    fun isIndicatorPressing(motionEvent: MotionEvent, recyclerView: RecyclerView): Boolean {
        when(motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                checkIfIndicatorPressing(motionEvent.x, motionEvent.y)?.let {
                    recyclerView.scrollToPosition(it)
                }
            }
        }
        return false
    }

}
