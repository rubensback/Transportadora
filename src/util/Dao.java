/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Rubens Back
 */
public class Dao {

    protected PreparedStatement stmt;
    protected String sql = "";
    protected ResultSet res;
    protected boolean debug = true;
    protected Connection conexao;

    public Dao() {
        conexao = Conexao.getConexao();
    }
}
