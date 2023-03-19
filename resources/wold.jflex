package lexerAndParser;

import static lexerAndParser.WorldParserSym.*;
import java_cup.runtime.*;

%%

%class WorldLexer
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

Number = [0-9]+
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\s\t\f]
Color = "#" ([a-z] | [0-9] )+
Name =  ([a-z] | "_" )( [a-z] | [A-Z] ) ([a-z] | [A-Z] | [0-9])+

%%

<YYINITIAL> {
	
	"\""						{return symbol(AP, yytext());	}/*commillas que encierra a las palabras claves*/
	"all"						{return symbol(ALL, yytext());	} /*Llama a los mundos guardados*/
	/* kerwords */
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
	"BRICK"						{ return symbol(BRICK, yytext());	} /*Muro*/
	"HALL"						{ return symbol(HALL, yytext()); }		/*Camino*/
    "targets"                   { return symbol(TARGETS, yytext()); } /*almacenamiento*/
    "player"                    { return symbol(PLAYER, yytext()); }

	/*peticiones de wolds*/
	"worlds"					{return symbol(WORLDS,yytext());	}/*Encierra a varios mundos*/
	"world"						{return symbol(WORLD, yytext());	}/*encierra aun solo mundo*/

	/* signs and operators */
	":"			{ return symbol( POINTS, yytext()); }
	"."			{ return symbol( POINT, yytext()); }
	","			{ return symbol( COMMA, yytext());  }
	"("			{ return symbol( LPAREN, yytext()); }
	")"			{ return symbol( RPAREN, yytext()); }
	"{"			{ return symbol(LKEY, yytext());	}
	"}"			{ return symbol(RKEY, yytext());	}
	"["			{ return symbol(LCORCH, yytext());	}
	"]"			{ return symbol(RCORCH, yytext());	}
	"+"			{ return symbol( PLUS, yytext());   }
	"-"			{ return symbol( MINUS, yytext());  }
	"*"			{ return symbol( TIMES, yytext());  }
	"/"			{ return symbol( DIV, yytext());    }
	"FLOOR"		{ return symbol( FLOOR, yytext());  }
	"CEIL"		{ return symbol( CEIL, yytext());   }
	
	

	/*Name*/
	{Name}			{ 	//imprimir(String.valueOf(yytext()));	
						return new Symbol(NAMEW, yyline+1,yycolumn+1, String.valueOf( yytext() ) ); }
	/* Numbers */
	{Number}		{ 	//imprimir(yytext());
						return new Symbol(ENTERO, yyline + 1, yycolumn + 1, Integer.valueOf(yytext())); }
	/*Color */
	{Color}			{ 	//imprimir(String.valueOf(yytext()));
						return new Symbol(COLOR,yyline+1,yycolumn+1, String.valueOf( yytext()) ); }
	/* whitespace */
	{WhiteSpace}		{ /* Ignore */ }

}

/* error */

[^]				{ return symbol(ERROR, yytext()); }