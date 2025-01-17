package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ingresos{
    static Scanner sc = new Scanner(System.in);
    public static void ingresos(String DNI) throws Exception {
        System.out.println(""" 
                Tipo de Ingresos
                1. Nomina
                2. Venta en páginas de segunda mano
                """);
        String numT = sc.next();
        int tipo = tipoCom(numT);

        System.out.println("Introduzca el Ingreso");
        String sIngreso = sc.next();
        int ganancia = gananciaCom(sIngreso);
        opciones(tipo, DNI, ganancia);
    }

    private static int irpf(int ganancia) {
        double aux = ganancia * 0.15;
        ganancia -= (int) aux;
        return ganancia;
    }

    private static int tipoCom(String numT) throws Exception {
        int tipo = 0;
        try{
            tipo = Integer.parseInt(numT);
        }catch (NumberFormatException e){
            throw new Exception("formato no valido");
        }
        if (tipo<0||tipo>2){
            throw new Exception("Tiene que ser un numero del 1 al 2");
        }
        return tipo;
    }

    private static int gananciaCom(String sIngreso) throws Exception {
        int Ingreso = 0;
        try{
            Ingreso = Integer.parseInt(sIngreso);
        }catch (NumberFormatException e){
            throw new Exception("formato no valido");
        }
        if (Ingreso<0){
            throw new Exception("Tiene que ser un numero positivo");
        }
        return Ingreso;
    }

    private static void opciones(int tipo, String DNI, int ganancia){
        switch (tipo){
            case 1:
                ganancia = irpf(ganancia);
                try(SQLite sql = new SQLite()){
                    Connection conn = sql.getConnection();
                    // Crear la consulta SQL
                    String sentencia = "INSERT INTO movimientos (DNI,tipo,saldo) VALUES (?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sentencia);
                    statement.setString(1,DNI);
                    statement.setString(2,"Nomina");
                    statement.setInt(3,ganancia);
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
                    statement.setString(2,"Venta en páginas de segunda mano");
                    statement.setInt(3,ganancia);
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
            saldo += ganancia;
            actu.setInt(1,saldo);
            actu.setString(2,DNI);
            actu.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
