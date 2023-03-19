package lexerAndParser;

import static lexerAndParser.WorldParserXMLSym.*;
import java_cup.runtime.*;

%%

%class WorldXmlLexer
%type java_cup.runtime.Symbol
%public
%cup
%unicode
%line
%column

%{

	private Symbol symbol(int type) {
		return new Symbol(type, yyline + 1, yycolumn + 1);
	}

	private Symbol symbol(int type, Object value) {
		//System.out.println("lexer: "+String.valueOf(value));
		return new Symbol(type, yyline + 1, yycolumn + 1, value);
	}
	private void imprimir(String text){
		System.out.println("lexer: "+text);
	}

%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\s\t\f]
Color = "#" ([a-z] | [0-9] )+
Name = ( [a-zA-Z] | "_" )+
Number = [0-9]+

%%


<YYINITIAL> {
	
	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" { return symbol(I, yytext());	} 
	"</"	                { return symbol(CIERRE, yytext()); }
	"<"						{ return symbol(INICIAL, yytext());	}
	">"		                { return symbol(FINAL, yytext()); }

	/*NAME - ROWS - COLS*/
	"name"		                { return symbol(NAME, yytext()); } /*nombre del mundo*/
	"rows"		                { return symbol(ROWS, yytext()); } /*fila del tablero*/
	"cols"		                { return symbol(COLS, yytext()); } /*columna del tablero*/
    
    /*Configuracion opcional del tablero*/
	/*CONFIG-BOXC-BOXCPOS-BOXCES*/
	"config"		            { return symbol(CONFIG, yytext()); } /*iniciar la configuracion*/
	"box_color"			        { return symbol(BOXC, yytext()); }   /*color de las cajas*/
	"box_on_target_color"		{ return symbol(BOXCPOS, yytext()); }/*color en la posicion*/
	"target_color"		        { return symbol(BOXCES, yytext()); } /*color en los espacios*/
	"brick_color"		        { return symbol(BRICKCOLOR, yytext()); }/*color de las paredes*/
	"hall_color"		        { return symbol(HALLCOLOR, yytext()); } /*color del camino*/
	"undefined_color"           { return symbol(UNDEFINEDCOLOR, yytext()); } /*color de las casillas indefinidas*/
	"player_color"		        { return symbol(PLAYERCOLOR, yytext()); }/*color del jugador*/
    
    /*Configuracion de los materiales en el tablero campo, cajas, almacenamiento y jugador*/
    "board"			            { return symbol(BOARD, yytext()); } /*iniciar el tablero*/
	"posX"			            { return symbol(POSX, yytext()); } /*posicion en X*/
	"posY"			            { return symbol(POSY, yytext()); } /*posicion en Y*/
    "boxes"                     { return symbol(BOXES, yytext()); } /*caja*/
    "type"                      { return symbol(TYPE, yytext());  } /*tipo */
	"BRICK"						{ return symbol(BRICK, yytext());	}
	"HALL"						{ return symbol(HALL, yytext()); }
    "targets"                   { return symbol(TARGETS, yytext()); } /*almacenamiento*/
    "player"                    { return symbol(PLAYER, yytext()); }
	"worlds"                    { return symbol(WORLDS, yytext()); }
	"world"                     { return symbol(WORLD, yytext()); }


	/* Numbers */
	{Number}		{ return new Symbol(ENTERO, yyline + 1, yycolumn + 1, Integer.valueOf(yytext())); }
	/*Color */
	{Color}			{ return new Symbol(COLOR,yyline+1,yycolumn+1, String.valueOf( yytext()) ); }
	/* whitespace */
	{WhiteSpace}		{ /* Ignore */ }
	{Name}			{ return new Symbol(NAMEW, yyline+1,yycolumn+1, String.valueOf( yytext() ) ); }

}

[^]				{ return symbol(ERROR, yytext()); }
