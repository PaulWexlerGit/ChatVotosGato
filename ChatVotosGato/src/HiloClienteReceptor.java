/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paul
 */
public class HiloClienteReceptor extends Thread {

    private Socket s;

    public HiloClienteReceptor(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
//            do {
                ois = new ObjectInputStream(s.getInputStream());
                int votos = (int) ois.readObject();
                System.out.println("votos recibidos: " + votos);
//            } while (true);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
