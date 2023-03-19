package com.example.proyecto1compi1_2023

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.proyecto1compi1_2023.databinding.ActivityWorldBinding
import com.example.proyecto1compi1_2023.objects.World
import com.example.proyecto1compi1_2023.socket.ConexionSocket.*
import java.io.Serializable


class WorldActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityWorldBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world)

        val llBotonera: LinearLayout =  findViewById<LinearLayout>(R.id.llBotoneraWorld)
        val btnBackMain : Button = findViewById<Button>(R.id.btnBackMainWorld)
        //Onclick for btnBackMain
        btnBackMain.setOnClickListener(View.OnClickListener { btnBackMain() })

        val message = intent.getCharSequenceArrayListExtra("WORLDS_NAME")
        println(message.toString())
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        if (message != null) {
            for (i in 0..message.size-1){
                val button : Button = Button(this)
                button.layoutParams = lp
                button.text = message.get(i)
                button.setOnClickListener(ButtonsOnClickListener(this));
                llBotonera.addView(button)
            }
        }
    }

    private fun btnBackMain(){
        setWorldN(null)
        val mainActivity = Intent(this, MainActivity::class.java);
        startActivity(mainActivity)
    }
    internal class ButtonsOnClickListener(context: Context) : View.OnClickListener {
        var context: Context

        init {
            this.context = context
        }

        override fun onClick(v: View) {
            val b = v as Button
            println("world: "+b.text)
            enviar("{ \"world\": \""+b.text+" \" }")
            while (true){
                if (getWorld() != null)
                    break
            }
            val mainActivity = Intent(context, PlayActivity::class.java)
            context.startActivity(mainActivity)
            //Toast.makeText(context, b.text, Toast.LENGTH_SHORT).show()
        }
    }
}