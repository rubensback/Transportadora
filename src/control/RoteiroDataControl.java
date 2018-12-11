/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.RoteiroData;
import model.dao.RoteiroDataDao;

/**
 *
 * @author Rubens Back
 */
public class RoteiroDataControl {

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy");

    private JTextField tfData;
    private JTable tbRoteiro;

    private RoteiroData roteiroData = null;
    private List<RoteiroData> listRoteiroData;
    private RoteiroDataDao roteiroDataDao;

    public RoteiroDataControl(JTextField tfData, JTable tbRoteiro) {
        this.tfData = tfData;
        this.tbRoteiro = tbRoteiro;

        listRoteiroData = new ArrayList();
        roteiroDataDao = new RoteiroDataDao();
    }

    public void cadastrarAction() throws ParseException {
        //Montar nova veiculo
        roteiroData = new RoteiroData();
        roteiroData.setDatar(sdf1.parse(tfData.getText()));

        boolean res = roteiroDataDao.salvar(roteiroData);
        if (res) {
            JOptionPane.showMessageDialog(null, "Cadastrado!");
            listarAction();
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }
    
    public void alterarAction() throws ParseException {
        roteiroData = getItemSelecionado();
        if (roteiroData == null) {
            JOptionPane.showMessageDialog(null, "Escolha um veiculo!");
        } else {
            roteiroData.setDatar(sdf1.parse(tfData.getText()));

            boolean res = roteiroDataDao.atualizar(roteiroData);
            if (res) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso.");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar.");
            }
        }
    }
    
    public void excluirAction() {
        roteiroData = getItemSelecionado();
        if (roteiroData == null) {
            JOptionPane.showMessageDialog(null, "Escolha um roteiro!");
        } else {
            boolean res = roteiroDataDao.excluir(roteiroData);
            if (res) {
                JOptionPane.showMessageDialog(null, "roteiro excluído!");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            }
        }
        roteiroData = null;
    }
    
    private RoteiroData getItemSelecionado() {
        int linha = tbRoteiro.getSelectedRow();
        if (linha >= 0) {
            return listRoteiroData.get(linha);
        } else {
            return null;
        }
    }
    
    public void listarAction() {
        listRoteiroData = roteiroDataDao.listar();
        showItensTable();
    }

    private void showItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) tbRoteiro.getModel();
        model.setNumRows(0);
        for (RoteiroData R : listRoteiroData) {
            model.addRow(new Object[]{
                R.getIdr(),
                R.getDatar(),});
        }
    }
}