package gestion;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca DNI");
        String dni = sc.next();
        if(comprobarDNI(dni)){
            System.out.println("paco");
        }
        System.out.println("1. Ingresos");
        System.out.println("2. Gastos");

        try{
            SQLite sqLite = new SQLite();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean comprobarDNI(String DNI){
        boolean correcto = false;
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        if(DNI.length()==9){
            try {
                char letra = DNI.toUpperCase().charAt(8);
                String auxS = DNI.substring(0,8);
                int num = Integer.parseInt(auxS);
                if(letras[num%23]==letra){
                    correcto=true;
                }
            }catch (NumberFormatException e){
                System.out.println("2. wqddwdw");
            }
        }
        return correcto;
    }

}