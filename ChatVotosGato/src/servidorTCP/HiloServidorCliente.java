/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidorTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import modelo.ObjetoCompartidoGatos;

/**
 *
 * @author paul
 */
class HiloServidorCliente extends Thread {

    private ObjetoCompartidoGatos oc;
    private Socket cliente;
    private int id;

    public HiloServidorCliente(ObjetoCompartidoGatos oc, Socket cliente, int id) {
        this.oc = oc;
        this.cliente = cliente;
        this.id = id;
    }

    @Override
    public void run() {
        String nombreGato;
        ObjectInputStream ois;
        ObjectOutputStream oos;
//        do {
            try {
                ois = new ObjectInputStream(cliente.getInputStream());
                oos = new ObjectOutputStream(cliente.getOutputStream());
                nombreGato = (String) ois.readObject();
                if (!nombreGato.equalsIgnoreCase("fin")) {
                    int votos = oc.addGato(nombreGato);
                    oos.writeObject(votos);
                }
            } catch (Exception ex) {
                System.err.println(" -> cierre abrupto del cliente");
//                break;
            }
//        } while (!nombreGato.equalsIgnoreCase("fin"));
        System.out.println("el hiloServidor con id  " + id + " ha terminado");
        try {
            cliente.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
