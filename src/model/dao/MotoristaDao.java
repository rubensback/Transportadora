package model.dao;

import interfaces.DaoI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Motorista;
import util.Conexao;
import util.Dao;

public class MotoristaDao extends Dao implements DaoI<Motorista> {
    
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public MotoristaDao() {
        super();
    }
    
    
    @Override
    public boolean salvar(Motorista obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "motorista(nome,nascimento,endereco,tipocnh,numcnh) "
                    + "VALUES(?,?,?,?,?)");
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getNascimento());
            stmt.setString(3, obj.getEndereco());
            stmt.setString(4, obj.getTipocnh());
            stmt.setString(5, obj.getNumcnh());
            
            int res = stmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Motorista obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Motorista obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Motorista> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Motorista lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
