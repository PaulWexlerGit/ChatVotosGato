/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo01;

import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author paul
 */
public class Ejemplo02 {
// {a=3, q=2, f=2, x=2, h=3, y=3, i=4, z=1, k=2}
    public static void main(String[] args) {
        TreeMap<String, Integer> cooperativas = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        String tipoUva = null;
        int kilos = 0;
        do {
            System.out.println("Introduce tipo de uva (*para terminar)");
            tipoUva = sc.nextLine();
            if (!tipoUva.equals("*")) {
                System.out.println("kilos");
                kilos = Integer.parseInt(sc.nextLine());
                if (cooperativas.containsKey(tipoUva)) {
                    cooperativas.put(tipoUva, kilos + cooperativas.get(tipoUva));
                } else {
                    cooperativas.put(tipoUva, kilos);
                }
            }
        } while (!tipoUva.equals("*"));
        System.out.println(cooperativas);
    }
}