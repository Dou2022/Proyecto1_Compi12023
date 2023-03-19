package com.example.proyecto1compi1_2023

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto1compi1_2023.socket.ConexionSocket


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val compileWorld : Button = findViewById(R.id.compileWorld)
        val clearTextWorld : Button= findViewById<Button>(R.id.clearTextWorld)
        val etWorldInput : EditText = findViewById(R.id.etWorldInput)
        val btnWorld : Button = findViewById<Button>(R.id.btn_world)

        // onClick for btnCompile
        compileWorld.setOnClickListener( View.OnClickListener { btnCompileAction(etWorldInput.text.toString()) } )
        // onclick for btnWorld
        btnWorld.setOnClickListener( View.OnClickListener { btnWoldAction()})

        // onClick for ClearTextWorld
        clearTextWorld.setOnClickListener( View.OnClickListener { etWorldInput.setText("") })

        //iniciar conexion socket
        ConexionSocket.ejecutarConexion()
    }
    private fun btnWoldAction(){
        ConexionSocket.enviar("{ \"worlds\": \"all\" }")
        var valor: ArrayList<String>
        while (true){
            if (ConexionSocket.getWorldN() != null){
                valor = ConexionSocket.getWorldN()
                break
            }
        }
        val activityWold = Intent(this, WorldActivity :: class.java ).apply {
            putExtra("WORLDS_NAME",valor)
        }
        startActivity(activityWold)

    }

    private fun btnCompileAction( textWorld : String ) {
        println(textWorld.length.toString()+" entrando al compilador "+ textWorld)
        if(textWorld.length>0){
            print("usuario ==> ")
            ConexionSocket.enviar(textWorld)
        }

        // cambio de ventana
        /*
        val activityPlay = Intent(this, PlayActivity::class.java);
        startActivity(activityPlay);
        */
    }
    private fun btnClearCompileAction(){

    }

}

