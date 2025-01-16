package gestion;

import java.util.Scanner;

public class Gastos {
    static Scanner sc = new Scanner(System.in);
    public static void gastos(String DNI) throws Exception {
        System.out.println(""" 
                Tipo de gastos
                1. Vacaciones
                2. Alquiler
                3. Vicios
                """);
        String numT = sc.next();
        int tipo = tipoCom(numT);

        System.out.println("Introduzca el gasto");
        String sGasto = sc.next();
        int coste = costeCom(sGasto);

        //comprobar si se puede relizar el gasto

        //insertar gasto
    }
    
    private static int tipoCom(String numT) throws Exception {
        int tipo = 0;
        try{
            tipo = Integer.parseInt(numT);
        }catch (NumberFormatException e){
            throw new Exception("formato no valido");
        }
        if (tipo<0||tipo>4){
            throw new Exception("Tiene que ser un numero del 1 al 4");
        }
        return tipo;
    }

    private static int costeCom(String sGasto) throws Exception {
        int gasto = 0;
        try{
            gasto = Integer.parseInt(sGasto);
        }catch (NumberFormatException e){
            throw new Exception("formato no valido");
        }
        if (gasto<0){
            throw new Exception("Tiene que ser un numero positivo");
        }
        return gasto;
    }
    
}
