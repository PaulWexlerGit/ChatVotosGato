/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo01;

import java.util.HashMap;

/**
 *
 * @author paul
 */
public class ObjetoCompartidoCooperativa {

    HashMap<String, Integer> cooperativa = new HashMap<>();

    public synchronized void addUva(Uva u){
        if (cooperativa.containsKey(u.getTipoUva())) {
            cooperativa.put(u.getTipoUva(), u.getKilos() + cooperativa.get(u.getTipoUva()));
        } else {
            cooperativa.put(u.getTipoUva(), u.getKilos());
        }
    }
}
