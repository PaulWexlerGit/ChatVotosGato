/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo01;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author paul
 */
public class Ejemplo01 {

    public static void main(String[] args) {
//        HashMap<String, Integer> cooperativas = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        String tipoUva = null;
        int kilos = 0;
        do {
            System.out.println("Introduce tipo de uva (*para terminar)");
            tipoUva = sc.nextLine();
            if (!tipoUva.equals("*")) {
                System.out.println("kilos");
                kilos = Integer.parseInt(sc.nextLine());
                Uva uva = new Uva(tipoUva, kilos);               
            }
        } while (!tipoUva.equals("*"));

    }

}
