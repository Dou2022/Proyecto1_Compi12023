package com.example.proyecto1compi1_2023

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Canvas
import android.graphics.Paint
import android.widget.FrameLayout
import com.example.proyecto1compi1_2023.figure.*
import com.example.proyecto1compi1_2023.objects.World
import com.example.proyecto1compi1_2023.socket.ConexionSocket

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
public class PlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        val btnReturnMain : Button = findViewById<Button>(R.id.btnReturnMain)
        val drawLayout: FrameLayout = findViewById(R.id.flDrawLayout)

        val container = ConexionSocket.getWorld()
        val panel = DrawPanel(this, container)
        drawLayout.addView(panel)


        //Return MainActivity
        btnReturnMain.setOnClickListener(View.OnClickListener { ReturnMain() })
    }
    private fun ReturnMain(){
        val activityMain = Intent(this, MainActivity :: class.java )
        startActivity(activityMain)
    }
}