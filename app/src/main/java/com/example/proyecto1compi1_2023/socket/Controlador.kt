package com.example.proyecto1compi1_2023.socket

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.proyecto1compi1_2023.WorldActivity
import java.io.IOException
import com.example.proyecto1compi1_2023.lexerAndParser.*
import com.example.proyecto1compi1_2023.objects.World
import java.io.StringReader

class Controlador {

    public fun recibirPeticion( dato: String){
        var lex : WorldXmlAndroidLexer = WorldXmlAndroidLexer(StringReader(dato));
        var parser : WorldParserAndroidXML = WorldParserAndroidXML(lex)
        try {
            parser.parse()
            if (parser.isParsed){
                if (parser.world != null){
                    println("------- ingreso a world ----")
                    ConexionSocket.setWorld(parser.world.get(0))
                }
                if (parser.worldN != null){
                    println("------- ingreso a worldN ----")
                    for (s : String in parser.worldN){
                        println(s)
                    }
                    ConexionSocket.setWorldN(parser.worldN)
                }

            }
        }catch (e: IOException){
            println(e.message)
        }
    }
    private fun worldActivity(){

    }
    private fun worldsPeticion(){
    }
}