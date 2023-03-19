package com.example.proyecto1compi1_2023.socket;

import com.example.proyecto1compi1_2023.objects.World;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConexionSocket {
    private static Socket socket;
    private static DataInputStream bufferDeEntrada = null;
    private static DataOutputStream bufferDeSalida = null;
    final static String COMANDO_TERMINACION = "salir()";
    final static String IP = "10.0.2.2"; // localhost
    //android studio tiene algunas puestas de enlace bloqueadas
    private static boolean estadoRecibir = false;
    private static List<World> worlds = null;
    private static World world = null;
    private static  ArrayList<String> worldN = null;
    final static int PUERTO = 5560;

    public static World getWorld(){ return world; }
    public static void setWorld(World w){ world = w; }
    public static void setEstadoRecibir(boolean estadoRecibira){
        estadoRecibir = estadoRecibira;
    }
    public static boolean getEstadoRecibir(){
        return estadoRecibir;
    }
    public static List<World> getWorlds(){
        return worlds;
    }
    public static void  setWorlds(ArrayList<World> worldss){
        worlds = worldss;
    }
    public static ArrayList<String> getWorldN(){ return worldN; }
    public static void setWorldN(ArrayList<String> valor){ worldN = valor; }
    public static void levantarConexion() {
        try {
            if (socket == null ){
                socket = new Socket(IP,PUERTO);
                mostrarTexto("Conectado a :" + socket.getInetAddress().getHostName());
            }
        } catch (Exception e) {
            mostrarTexto("Excepción al levantar conexión: " + e.getMessage());
            System.exit(0);
        }
    }

    public static void mostrarTexto(String s) {
        System.out.println(s);
    }

    public static void abrirFlujos() {
        try {
            bufferDeEntrada = new DataInputStream(socket.getInputStream());
            bufferDeSalida = new DataOutputStream(socket.getOutputStream());
            bufferDeSalida.flush();
        } catch (IOException e) {
            mostrarTexto("Error en la apertura de flujos");
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

    public static void cerrarConexion() {
        try {
            bufferDeEntrada.close();
            bufferDeSalida.close();
            socket.close();
            mostrarTexto("Conexión terminada");
        } catch (IOException e) {
            mostrarTexto("Error CerrarConexion: "+e.getMessage());
        }finally{
            System.exit(0);
        }
    }

    public static void ejecutarConexion() {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    levantarConexion();
                    abrirFlujos();
                    recibirDatos();
                } finally {
                    cerrarConexion();
                }
            }
        });
        hilo.start();
    }

    public static void recibirDatos() {
        String st = "";
        try {
            do {
                estadoRecibir = false;
                Controlador control = new Controlador();
                st = (String) bufferDeEntrada.readUTF();
                mostrarTexto("\n[Servidor] => " + st);
                System.out.print("\n[Usted] => ");
                control.recibirPeticion(st);
                estadoRecibir = true;
            } while (!st.equals(COMANDO_TERMINACION));
        } catch (IOException e) {
            mostrarTexto("Error RecibirDatos: "+e.getMessage());
        }
    }
}
