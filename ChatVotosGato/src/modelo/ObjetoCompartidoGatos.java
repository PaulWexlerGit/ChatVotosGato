/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paul
 */
public class ObjetoCompartidoGatos {

    private HashMap<String, Integer> listaNombres;

    public ObjetoCompartidoGatos() {
        listaNombres = new HashMap<String, Integer>();
    }

    public synchronized int addGato(String nombre) {
        int votos;
        if (listaNombres.containsKey(nombre.toLowerCase())) {
            votos = listaNombres.get(nombre.toLowerCase());
//            Integer votos = votos.intValue()+1;
            votos++;
            listaNombres.put(nombre.toLowerCase(), votos);
        } else {
            listaNombres.put(nombre.toLowerCase(), 1);
            votos = 1;
        }
        System.out.println("Votos emitidos");
        for (Map.Entry<String, Integer> entry : listaNombres.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " : " + val + " votos");
        }
        System.out.println("");
        return votos;
    }
}
