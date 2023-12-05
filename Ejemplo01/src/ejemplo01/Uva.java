/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo01;

import java.io.Serializable;

/**
 *
 * @author paul
 */
public class Uva implements Serializable{
    private String tipoUva;
    private int kilos;

    public Uva(String tipoUva, int kilos) {
        this.tipoUva = tipoUva;
        this.kilos = kilos;
    }

    public String getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(String tipoUva) {
        this.tipoUva = tipoUva;
    }

    public int getKilos() {
        return kilos;
    }

    public void setKilos(int kilos) {
        this.kilos = kilos;
    }
    
}
