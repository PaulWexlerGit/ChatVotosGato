/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.entregableclienteftp;

import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author paul
 */
public class EntregableClienteFTP {

    private static final String servFTP = "localhost";
    private static final String usuario = "Usuario1";
    private static final String clave = "root";
    private static final FTPClient cliente = new FTPClient();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada;
        do {
            System.out.println("Introduzca una opción:");
            entrada = sc.nextLine();
            switch (entrada) {
                case "login":
                    conectar();
                    break;
                case "list":
                    listarArchivos();
                    break;
                case "Disconnect":
                    desconectar();
                    break;
            }
        } while (true);

    }

    public static void conectar() {

        System.out.println("Nos conectamos a: " + servFTP);
        try {
            cliente.connect(servFTP);
            cliente.enterLocalPassiveMode(); //modo pasivo

            boolean login = cliente.login(usuario, clave);
            if (login) {
                System.out.println("Login correcto...");
            } else {
                System.out.println("Login Incorrecto...");
                cliente.disconnect();
                System.exit(1);
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
            System.out.println("Directorio actual: "
                    + cliente.printWorkingDirectory());
            FTPFile[] files = cliente.listFiles();
            System.out.println("Ficheros en el directorio actual:"
                    + files.length);
            //array para visualizar el tipo de fichero
            String tipos[] = {"Fichero", "Directorio", "Enlace simb."};

            for (int i = 0; i < files.length; i++) {
                System.out.println("\t" + files[i].getName() + " => "
                        + tipos[files[i].getType()]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
