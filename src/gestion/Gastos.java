package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        if(gatosVer(coste,DNI)){
            opciones(tipo,DNI,coste);
        }else{
            System.out.println("Más dinero del que hay en la cuenta");
        }

    }
    
    private static int tipoCom(String numT) throws Exception {
        int tipo = 0;
        try{
            tipo = Integer.parseInt(numT);
        }catch (NumberFormatException e){
            throw new Exception("formato no valido");
        }
        if (tipo<0||tipo>3){
            throw new Exception("Tiene que ser un numero del 1 al 3");
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

    private static boolean gatosVer(int gasto, String DNI){
        boolean res = false;
        try(SQLite sql = new SQLite()){
            Connection conn = sql.getConnection();
            // Crear la consulta SQL
            String sentencia = "SELECT saldo from usuarios WHERE DNI = ? ";
            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setString(1,DNI);
            ResultSet resultSet = statement.executeQuery();
            int saldo = resultSet.getInt("saldo");
            sentencia = "UPDATE usuarios SET saldo = ? WHERE DNI = ? ";
            PreparedStatement actu = conn.prepareStatement(sentencia);
            saldo -= gasto;
            if (saldo>0){
                res = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }


    private static void opciones(int tipo, String DNI, int gasto){
        switch (tipo){
            case 1:
                try(SQLite sql = new SQLite()){
                    Connection conn = sql.getConnection();
                    // Crear la consulta SQL
                    String sentencia = "INSERT INTO movimientos (DNI,tipo,saldo) VALUES (?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sentencia);
                    statement.setString(1,DNI);
                    statement.setString(2,"Vacaciones");
                    statement.setInt(3,gasto);
                    statement.executeUpdate();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try(SQLite sql = new SQLite()){
                    Connection conn = sql.getConnection();
                    // Crear la consulta SQL
                    String sentencia = "INSERT INTO movimientos (DNI,tipo,saldo) VALUES (?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sentencia);
                    statement.setString(1,DNI);
                    statement.setString(2,"Alquiler");
                    statement.setInt(3,gasto);
                    statement.executeUpdate();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try(SQLite sql = new SQLite()){
                    Connection conn = sql.getConnection();
                    // Crear la consulta SQL
                    String sentencia = "INSERT INTO movimientos (DNI,tipo,saldo) VALUES (?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sentencia);
                    statement.setString(1,DNI);
                    statement.setString(2,"Vicios");
                    statement.setInt(3,gasto);
                    statement.executeUpdate();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        try(SQLite sql = new SQLite()){
            Connection conn = sql.getConnection();
            // Crear la consulta SQL
            String sentencia = "SELECT saldo from usuarios WHERE DNI = ? ";
            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setString(1,DNI);
            ResultSet resultSet = statement.executeQuery();
            int saldo = resultSet.getInt("saldo");
            sentencia = "UPDATE usuarios SET saldo = ? WHERE DNI = ? ";
            PreparedStatement actu = conn.prepareStatement(sentencia);
            saldo -= gasto;
            actu.setInt(1,saldo);
            actu.setString(2,DNI);
            actu.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
