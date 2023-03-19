package socket;

import Objects.World;
import control.ConstructorXml;
import static control.FileControl.readData;
import static control.FileControl.writeFile;
import java.io.File;
import java.io.StringReader;
import java.util.List;
import lexerAndParser.WorldLexer;
import lexerAndParser.WorldParser;
import lexerAndParser.WorldParserXML;
import lexerAndParser.WorldXmlLexer;
import socket.ConexionSocket;

public class Controlador {
    
        public static void parseResponse(String response) {
            
        WorldLexer lex = new WorldLexer(new StringReader(response));
        WorldParser parser = new WorldParser(lex);
        
        try {
            parser.parse();
            ConstructorXml request = new ConstructorXml();
            if (parser.isParsed()) {
                if(parser.getWorld() != null){
                    System.out.println("-------getWorld -----------");
                    File file = new File("./sokoban.xml");
                    writeFile(file,request.xmlData(parser.getWorld()));
                }
                if (parser.getSolicitudWolds() != null) {
                    System.out.println("------- getSolicitudWorlds -----------");
                    String lexx = readData("./sokoban.xml");
                    parseRequestAll(lexx);
                }
                if (parser.getSelectWorld() != null) {
                    System.out.println("------ getSelectWorld -----------");
                    String lexx = readData("./sokoban.xml");
                    parserRequestWorld(lexx,parser.getSelectWorld());
                    
                }
                
            }else{
                System.out.println("-----Error------");
            }
                
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace(System.out);
        }
    }
    private static void parseRequestAll(String response){
        WorldXmlLexer lex = new WorldXmlLexer(new StringReader(response));
        WorldParserXML parser = new WorldParserXML(lex);
        
        try {
            parser.parse();
            ConstructorXml request = new ConstructorXml();
            if (parser.isParsed()) 
                ConexionSocket.enviar(request.xmlDataWorlds(parser.getWorld()));
            
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace(System.out);
        }
    }
    private static void parserRequestWorld(String response,String select){
        WorldXmlLexer lex = new WorldXmlLexer(new StringReader(response));
        WorldParserXML parser = new WorldParserXML(lex);
        try {
            parser.parse();
            ConstructorXml request = new ConstructorXml();
            if (parser.isParsed()) 
                for (World world : parser.getWorld()) {
                    if (world.getName().contentEquals(select)) {
                        ConexionSocket.enviar(request.xmlDataWorld(world));
                    }
                }
            
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace(System.out);
        }
    }
}
