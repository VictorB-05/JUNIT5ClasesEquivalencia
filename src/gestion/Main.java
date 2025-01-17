package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try( SQLite sqLite = new SQLite()){
            sqLite.inicio();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        int input = 0;
        String dni ="";
        while (input != 2){
            System.out.println("1. Ingresar");
            System.out.println("2. Salir");
            input=sc.nextInt();
            int input1 = 0;
            if(input == 1){
                System.out.println("Introduzca DNI");
                dni = sc.next();
                if(!comprobarDNI(dni)){
                    System.out.println("DNI no valido");
                    input1 = 3;
                }
            } else if (input == 2) {
                System.exit(0);
            }else{
                System.out.println("Valor no valido");
                input = 0;
            }
            while(input1!=3){
                System.out.println("1. Ingresos");
                System.out.println("2. Gastos");
                System.out.println("3. Salir");
                input1=sc.nextInt();
                try{
                    switch (input1){
                        case 1:
                            Ingresos.ingresos(dni);
                            break;
                        case 2:
                            Gastos.gastos(dni);
                            break;
                        case 3:
                            System.out.println("Gracias por usar la aplicaion");
                            break;
                        default:
                            System.out.println("Opción no valida");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
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
                    correcto = baseDNI(DNI);
                }
            }catch (NumberFormatException e){
                System.out.println("2. wqddwdw");
            }
        }
        return correcto;
    }

    public static boolean baseDNI(String DNI){
        boolean aux = false;
        try( SQLite sqLite = new SQLite()){
            Connection conn = sqLite.getConnection();
            // Crear la consulta SQL
            String sentencia = "SELECT * FROM usuarios WHERE DNI = ?";
            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setString(1,DNI);
            if(statement.executeQuery().next()){
                System.out.println((DNI));
                aux= true;
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aux;
    }

}