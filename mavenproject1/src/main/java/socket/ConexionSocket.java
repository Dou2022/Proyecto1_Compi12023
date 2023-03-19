
package socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ConexionSocket {
    
    static final String HOST = "localhost";
    static final int PUERTO=5560;
    
     private static Socket socket;
    private static ServerSocket serverSocket;
    private static DataInputStream bufferDeEntrada = null;
    private static DataOutputStream bufferDeSalida = null;
    private static String dataInput = "";
    static Scanner escaner = new Scanner(System.in);
    final static String COMANDO_TERMINACION = "salir()";
    
    private static Socket conexion;
    
    public ConexionSocket(){
        
        
    }
    
    //Iniciar conexion
    private static void levantarConexion(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            mostrarTexto("Esperando conexión entrante en el puerto " + String.valueOf(puerto) + "...");
            socket = serverSocket.accept();
            mostrarTexto("Conexión establecida con: " + socket.getInetAddress().getHostName() + "\n\n\n");
        } catch (Exception e) {
            mostrarTexto("Error en levantarConexion(): " + e.getMessage());
            System.exit(0);
        }
    }
    
    private static void flujos() {
        try {
            bufferDeEntrada = new DataInputStream(socket.getInputStream());
            bufferDeSalida = new DataOutputStream(socket.getOutputStream());
            bufferDeSalida.flush();
        } catch (IOException e) {
            mostrarTexto("Error en la apertura de flujos");
        }
    }

    public static void recibirDatos() {
        String st = "";
        try {
            do {
                
                st = (String) bufferDeEntrada.readUTF();
                mostrarTexto("\n[Cliente] => " + st);
                System.out.print("\n[Usted] => ");
                Controlador.parseResponse(st);
                
            } while (!st.equals(COMANDO_TERMINACION));
        } catch (IOException e) {
            cerrarConexion();
        }
    }

      public static void enviar(String s) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mostrarTexto(s);
                try {
                    bufferDeSalida.writeUTF(s);
                    bufferDeSalida.flush();
                } catch (IOException e) {
                    mostrarTexto("Error al enviar: "+e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static void mostrarTexto(String s) {
        System.out.print(s);
    }

    public static void escribirDatos() {
        while (true) {
            System.out.print("[Usted] => ");
            enviar(escaner.nextLine());
           
        }
    }

    public static void ejecutarConexion() {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        levantarConexion(PUERTO);
                        flujos();
                        recibirDatos();
                    } finally {
                        cerrarConexion();
                    }
                }
            }
        });
        hilo.start(); 
    }
    
    public static void cerrarConexion() {
        try {
            bufferDeEntrada.close();
            bufferDeSalida.close();
            socket.close();
        } catch (IOException e) {
          mostrarTexto("Excepción en cerrarConexion(): " + e.getMessage());
        } finally {
            mostrarTexto("Conversación finalizada....");
            System.exit(0);

        }
    }
    public void setDataInput(String data){
        dataInput =  data;
    }
    public String getDataInput(){
        return dataInput;
    }
    
}
