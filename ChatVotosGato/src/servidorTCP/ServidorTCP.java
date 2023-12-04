/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidorTCP;

import java.net.ServerSocket;
import java.net.Socket;
import modelo.ObjetoCompartidoGatos;

/**
 *
 * @author paul
 */
public class ServidorTCP {
            public static void main(String[] args) {
        ObjetoCompartidoGatos oc = new ObjetoCompartidoGatos();
        Socket cliente;
        int id = 0;
        try (ServerSocket ss = new ServerSocket(6666)) {
            do {
                cliente = ss.accept();
                id++;
                HiloServidorCliente hsc = new HiloServidorCliente(oc, cliente, id);
                hsc.start();
            } while (true);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
