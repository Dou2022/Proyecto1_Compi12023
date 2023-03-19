package com.example.proyecto1compi1_2023.figure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import com.example.proyecto1compi1_2023.objects.World

class DrawPanel(context: Context?, val container: World) : View (context) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        container.getBoard().forEach(){
            val paint = Paint()
            if (it.type .equals("BRICK"))
                paint.setColor(Color.parseColor(container.config!!.brickColor))
            if (it.type .equals("HALL"))
                paint.setColor(Color.parseColor(container.config!!.hallColor))
            val cuadrado = Rect(it.posX*(800/container.rows), it.posY*(800/container.cols), it.posX*(800/container.rows)+(800/container.rows), it.posY*(800/container.cols)+(800/container.cols))
            if (canvas != null) {
                canvas.drawRect(cuadrado, paint)
            }
        }
        container.getBoxes().forEach(){
            val paint = Paint()
            paint.setColor(Color.parseColor("#ffff00"))
            val cuadrado = Rect(it.posX*(800/container.rows), it.posY*(800/container.cols), it.posX*(800/container.rows)+(800/container.rows), it.posY*(800/container.cols)+(800/container.cols))
            if (canvas != null) {
                canvas.drawRect(cuadrado, paint)
            }
        }
        container.getTargets().forEach(){
            val paint = Paint()
            paint.setColor(Color.parseColor("#d6fe0a"))
            val cuadrado = Rect(it.posX*(800/container.rows), it.posY*(800/container.cols), it.posX*(800/container.rows)+(800/container.rows), it.posY*(800/container.cols)+(800/container.cols))
            if (canvas != null) {
                canvas.drawRect(cuadrado, paint)
            }
        }
        val paint = Paint()
        paint.setColor(Color.parseColor("#ff00f7"))
        val cuadrado = Rect(container.player!!.posX*(800/container.rows), container.player!!.posY*(800/container.cols), container.player!!.posX*(800/container.rows)+(800/container.rows), container.player!!.posY*(800/container.cols)+(800/container.cols))
        if (canvas != null) {
            canvas.drawRect(cuadrado, paint)
        }

        /*
        // TODO: dibujar figuras que ya fueran pintadas
        container.figures.forEach {
            drawFigure(it, canvas)
        }

        // TODO: dibujar la nueva figura
        if (container.currentFigure != null) {
            drawFigure(container.currentFigure!!, canvas)
        }
        */
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
