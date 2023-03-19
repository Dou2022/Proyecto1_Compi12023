package com.example.proyecto1compi1_2023.figure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class DrawPanel(context: Context?, val container: FigureContainer) : View (context) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // TODO: dibujar figuras que ya fueran pintadas
        container.figures.forEach {
            drawFigure(it, canvas)
        }

        // TODO: dibujar la nueva figura
        if (container.currentFigure != null) {
            drawFigure(container.currentFigure!!, canvas)
        }
    }

    private fun drawFigure(figure: Figure, canvas: Canvas?) {
        val paint = Paint()
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 5f

        if (figure is Square) {
            drawQuadrilateral(figure.posX.toFloat(), figure.poxY.toFloat(), figure.size.toFloat(), figure.size.toFloat(), canvas, paint);
        }
    }

    private fun drawQuadrilateral(posX: Float, posY: Float, width: Float, height: Float, canvas: Canvas?, paint: Paint) {
        canvas?.drawRect(posX, posY, posX + width, posY + height, paint);
    }
}
