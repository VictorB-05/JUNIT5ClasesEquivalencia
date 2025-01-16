package gestion;

import java.util.Scanner;

public class Ingresos extends Exception{
    static Scanner sc = new Scanner(System.in);
    public void gastos() throws Exception {
        System.out.println(""" 
                Tipo de gastos
                1. Nomina
                2. Venta en páginas de segunda mano
                """);
        String numT = sc.next();
        int tipo = tipoCom(numT);

        System.out.println("Introduzca el gasto");
        String sGasto = sc.next();
        int coste = costeCom(sGasto);

        //comprobar si se puede relizar el gasto

        //insertar gasto
    }
    
    private int tipoCom(String numT) throws Exception {
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

    private int costeCom(String sGasto) throws Exception {
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
