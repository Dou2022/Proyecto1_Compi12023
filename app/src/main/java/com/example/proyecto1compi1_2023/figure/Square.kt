package com.example.proyecto1compi1_2023.figure

import com.example.proyecto1compi1_2023.lexerAndParser.Token

class Square(val token: Token, val posX: Int, val poxY: Int, val size: Int) : Figure(token) {

    override fun toString(): String {
        return "Square(token=$token, posX=$posX, poxY=$poxY, size=$size)"
    }
}