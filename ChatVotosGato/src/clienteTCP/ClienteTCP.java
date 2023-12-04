/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paul
 */
public class ClienteTCP {

    static Scanner sc;

    public static void main(String[] args) {
        String nombreGato;
        sc = new Scanner(System.in);
        ObjectOutputStream oos = null;
//        HiloClienteReceptor hcr = null;                                          // si el cliete espera respuesta con hilo
        ObjectInputStream ois = null;   //
        try (Socket s = new Socket("localhost", 6666)) {//si quieres conectarte  ti mismo poner localHost
//            hcr = new HiloClienteReceptor(s);                                     //si el cliete espera respuesta con hilo
//            hcr.start();                                                          //si el cliete espera respuesta con hilo
//            do {                      en caso de votar varias veces descomenta esto
            System.out.println("Introduzca el nombre que propone para el gato:  fin para terminar");
            nombreGato = sc.nextLine();
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(nombreGato);
//                hcr.join(); // espera a que el hilo termine   si solo se puede dar una respuesta   si el cliete espera respuesta con hilo
            ois = new ObjectInputStream(s.getInputStream());  //
            int votos = (int) ois.readObject();                  //
            System.out.println("votos recibidos: " + votos);     //
//            } while (!nombreGato.equalsIgnoreCase("fin"));         //en caso de votar varias veces descomenta esto
            System.out.println("cierre controlado del cliente");
        } catch (IOException e) {
            System.err.println("Error en la conexión");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        } //        catch (InterruptedException ex) {                                          // si el cliete espera respuesta con hilo
        //            System.err.println(ex.getMessage());                                     // si el cliete espera respuesta con hilo
        //        }                                                                            // si el cliete espera respuesta con hilo
        finally {
            if (oos != null) {
                try {
                    oos.close();

                } catch (IOException ex) {
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}
