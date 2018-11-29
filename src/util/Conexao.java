/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Rubens Back
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alunos
 */
public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/transportadora";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private static Connection conn = null;

    public static Connection getConexao() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectou...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(conn == null){
                try{
                    conn.close();
                } catch(Exception e){}
            }
        }
        return conn;
    }

}
