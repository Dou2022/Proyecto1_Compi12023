

package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileControl {
    public static String readData2(String path) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String string = "";
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (path);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            string +=linea;
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return string;
    }
        
    public static String readData(String path) {
        String string = "";

        try {
        File archivo = new File(path);
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        document.getDocumentElement().normalize(); 
        string = particionarXml(document);
        
        } catch(Exception e){
           e.printStackTrace();
        }
        
        return string;
   }
    public static void writeFile(File file, String txt) {
        try {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.write(txt);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    private static String particionarXml(Document document){
        String aa = "";
        String d = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        try {
            NodeList listaWorld = document.getElementsByTagName("world");
            for (int temp = 0; temp < listaWorld.getLength(); temp++) {
                String a = "";
                Node nodo = listaWorld.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    a += imprimir(element,"name");
                    a += imprimir(element,"rows");
                    a += imprimir(element,"cols");
                    a += particionarConfig(element.getElementsByTagName("config"));
                    a += particionarBoard(element.getElementsByTagName("board"));
                    a += particionarBoxes(element.getElementsByTagName("boxes"),"boxes");
                    a += particionarBoxes(element.getElementsByTagName("targets"),"targets");
                    a += particionarBoxes(element.getElementsByTagName("player"),"player");
                    aa +=  "<world>\n"+a+"</world>\n";
                    
                }
            }
            aa = d+"<worlds>\n"+aa+"</worlds>\n";
            System.out.println(aa);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aa;
    }
    private static String particionarConfig(NodeList list){
        String c = "";
        if (list.getLength() == 1) {
            Node nodo = list.item(0);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                c += imprimir(element,"box_color");
                c += imprimir(element,"box_on_target_color");
                c += imprimir(element,"target_color");
                c += imprimir(element,"brick_color");
                c += imprimir(element,"hall_color");
                c += imprimir(element,"undefined_color");
                c += imprimir(element,"player_color");
            }
            c = "<config>\n"+c+"</config>\n" ;
        }
        return c;
    }
    private static String particionarBoard(NodeList list){
        String c = "";
        for (int i = 0; i < list.getLength(); i++) {
            String cc = "";
            Node nodo = list.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                cc += imprimir(element,"posX");
                cc += imprimir(element,"posY");
                cc += imprimir(element,"type");
            }
            c += "<board>\n"+cc+"</board>\n";
        }
        return c;
    }
    private static String particionarBoxes(NodeList list,String dato){
        String c = "";
        for (int i = 0; i < list.getLength(); i++) {
            String cc = "";
            Node nodo = list.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                cc += imprimir(element,"posX");
                cc += imprimir(element,"posY");
            }
            c += "<"+dato+">\n"+cc+"</"+dato+">\n";
        }
        return c;
    }
    
    private static String imprimir(Element element,String dato){
        if (element.getElementsByTagName(dato).getLength()== 1) 
            return "\t<"+dato+">"+ element.getElementsByTagName(dato).item(0).getTextContent() + "</"+dato+">\n" ;
        
        return "";
    }
}
