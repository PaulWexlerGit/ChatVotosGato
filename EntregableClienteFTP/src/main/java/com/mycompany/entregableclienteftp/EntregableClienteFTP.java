/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.entregableclienteftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author paul
 */
public class EntregableClienteFTP {

    private static final FTPClient cliente = new FTPClient();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada;

        do {
            System.out.println("Introduzca comando:");
            entrada = sc.nextLine();
            String[] lineaComando = entrada.split(" ");
            if (lineaComando.length >= 1) {
                String comando = lineaComando[0];
                switch (comando) {
                    case "connect":
                        conectar(lineaComando[1], lineaComando[2]);
                        break;
                    case "List":
                        listarArchivos();
                        break;
                    case "changePath":
                        cambiarDirectorio(lineaComando[1]);
                        break;
                    case "down":
                        descargarFichero(lineaComando[1]);
                        break;
                    case "up":
                        subirFichero(lineaComando[1]);
                        break;
                    case "Disconnect":
                        desconectar();
                        break;
                    default:
                        System.out.println("comando incorrecto");
                }
            }

        } while (true);

    }

    public static void conectar(String server, String credenciales) {
        String[] usuPas = credenciales.split("/");
        System.out.println("Nos conectamos a: " + server);
        try {
            cliente.connect(server);
            cliente.enterLocalPassiveMode(); //modo pasivo

            boolean login = cliente.login(usuPas[0], usuPas[1]);
            if (login) {
                System.out.println("Login correcto...");
            } else {
                System.out.println("Login Incorrecto...");
                cliente.disconnect();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void desconectar() {
        try {
            boolean logout = cliente.logout();
            if (logout) {
                System.out.println("Logout del servidor FTP...");
            } else {
                System.out.println("Error al hacer Logout...");
            }
            cliente.disconnect();
            System.out.println("Desconectado...");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void listarArchivos() {
        try {
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
            FTPFile[] files = cliente.listFiles();
            System.out.println("Ficheros en el directorio actual:"
                    + files.length);
            //array para visualizar el tipo de fichero
            String tipos[] = {"Fichero", "Directorio", "Enlace simb.", "Desconocido"};  //damos significado al codigo devuelto por .getType()
            for (int i = 0; i < files.length; i++) {
                System.out.println("\t" + files[i].getName() + " => "
                        + tipos[files[i].getType()]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void cambiarDirectorio(String directorio) {
        try {
            boolean cambioDirectorio = cliente.changeWorkingDirectory(directorio);
            if (cambioDirectorio) {
                System.out.println("El directorio actual es: " + cliente.printWorkingDirectory());
            } else {
                System.out.println("El directorio introducido no es correcto");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void descargarFichero(String fichero) {

        try {
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            boolean descargaFichero = cliente.retrieveFile(fichero, new FileOutputStream(fichero));
            if (descargaFichero) {
                System.out.println("se ha descargado el fichero: " + fichero);
            } else {
                System.out.println("El fichero " + fichero + " no existe en el servidor");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void subirFichero(String fichero) {

        try {
            File archivo = new File(fichero);
            FileInputStream fis = new FileInputStream(archivo);
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            boolean cargaFichero = cliente.storeFile(fichero, fis);

            if (cargaFichero) {
                System.out.println("se ha cargado el fichero: " + fichero);
            } else {
                System.out.println("no se ha podido cargar el fichero " + fichero);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
