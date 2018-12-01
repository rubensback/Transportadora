package model.dao;

import interfaces.DaoI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        this.sql = "UPDATE motorista SET "
                + " nome = ?,"
                + " nascimento = ?,"
                + " endereco = ?,"
                + " numcnh = ?,"
                + " tipocnh = ?"
                + " WHERE "
                + " idmotorista = ?";
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getNascimento());
            stmt.setString(3, obj.getEndereco());
            stmt.setString(4, obj.getNumcnh());
            stmt.setString(5, obj.getTipocnh());
            stmt.setInt(6, obj.getIdm());
            return stmt.executeUpdate()>0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            if(debug)  System.out.println(sql);
        }
    }

    @Override
    public boolean excluir(Motorista obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM motorista "
                    + " WHERE idmotorista = ?");
            stmt.setInt(1, obj.getIdm());

            //Verifica a quantidade de linhas afetadas
            return (stmt.executeUpdate() > 0);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Motorista> listar() {
        this.sql = "SELECT "
                + " idmotorista, nome, nascimento,endereco,tipocnh,numcnh"
                + " FROM "
                + " motorista"
                + " ORDER BY idmotorista DESC";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            res = this.stmt.executeQuery();
            List<Motorista> lista = new ArrayList<>();
            while (res.next()) {
                Motorista m = new Motorista();
                m.setIdm(res.getInt("idmotorista"));
                m.setNome(res.getString("nome"));
                m.setNascimento(res.getString("nascimento"));
                m.setEndereco(res.getString("endereco"));
                m.setTipocnh(res.getString("tipocnh"));
                m.setNumcnh(res.getString("numcnh"));
                lista.add(m);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            if (debug) {
                System.out.println(sql);
            }
        }
    }

    @Override
    public Motorista lerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Motorista> pesquisar(String termo) {
        this.sql = "SELECT "
                + " idmotorista, nome, nascimento,endereco,tipocnh,numcnh "
                + " FROM "
                + " motorista"
                + " WHERE "
                + " idmotorista = ? OR nome LIKE ? OR nascimento LIKE ? OR endereco LIKE ? OR tipocnh LIKE ? OR numcnh LIKE ?"
                + " ORDER BY nome";
        try {
            this.stmt = conexao.prepareStatement(this.sql);
            this.stmt.setString(1, termo);
            this.stmt.setString(2, termo+"%");
            this.stmt.setString(3, termo+"%");
            this.stmt.setString(4, termo+"%");
            this.stmt.setString(5, termo+"%");
            this.stmt.setString(6, termo+"%");
            res = this.stmt.executeQuery();
            List<Motorista> lista = new ArrayList<>();
            while(res.next()){
                Motorista m = new Motorista();
                m.setIdm(res.getInt("idmotorista"));
                m.setNome(res.getString("nome"));
                m.setNascimento(res.getString("nascimento"));
                m.setEndereco(res.getString("endereco"));
                m.setTipocnh(res.getString("tipocnh"));
                m.setNumcnh(res.getString("numcnh"));
                lista.add(m);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally{
            if(debug){
                System.out.println(sql);
            }
        }
    }

}
