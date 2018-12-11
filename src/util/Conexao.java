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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            //System.out.println("Conectou...");
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
    
        private static void fecha(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        if(conn != null){
            conn.close();
        }
        
        if(stmt != null){
            stmt.close();
        }
        if(rs != null){
            rs.close();
        }
    }
    
    public static void fechaConexao(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        fecha(conn, stmt, rs);
    }
    public static void fechaConexao(Connection conn, Statement stmt) throws Exception{
        fecha(conn, stmt, null);
    }
    public static void fechaConexao(Connection conn) throws Exception{
        fecha(conn, null, null);
    }

}
