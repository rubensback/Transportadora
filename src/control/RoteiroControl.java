package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Motorista;
import model.Objeto;
import model.RoteiroLista;
import model.Veiculo;
import model.dao.RoteiroDao;

/**
 *
 * @author Rubens Back
 */
public class RoteiroControl {

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy");

    private JTable tbRoteiro;
    private JTextField tfPm;
    private JTextField tfPo;
    private JTextField tfPv;

    private RoteiroLista roteiro = null;
    private List<RoteiroLista> listRoteiro;
    private RoteiroDao roteiroDao;

    public RoteiroControl(JTable tbRoteiro, JTextField tfPm, JTextField tfPo, JTextField tfPv) {
        this.tbRoteiro = tbRoteiro;
        this.tfPm = tfPm;
        this.tfPo = tfPo;
        this.tfPv = tfPv;

        listRoteiro = new ArrayList<>();
        roteiroDao = new RoteiroDao();
    }

    public void excluirAction() {
        roteiro = getItemSelecionado();
        if (roteiro == null) {
            JOptionPane.showMessageDialog(null, "Escolha um roteiro!");
        } else {
            System.out.println(roteiro.getMotorista().getIdm());
            boolean res = roteiroDao.excluir(roteiro);
            if (res) {
                JOptionPane.showMessageDialog(null, "Roteiro excluído!");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            }
        }
        roteiro = null;
    }

    public void listarAction() {
        listRoteiro = roteiroDao.listar();
        showItensTable();
    }

    private void showItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) tbRoteiro.getModel();
        model.setNumRows(0);
        for (RoteiroLista r : listRoteiro) {
            model.addRow(new Object[]{
                r.getRoteiroData().getDatar(),
                r.getMotorista().getIdm(),
                r.getVeiculo().getIdv(),
                r.getObjeto().getIdo(),
                r.getIdrl()
            });
        }
    }

    public RoteiroLista getItemSelecionado() {
        int linha = tbRoteiro.getSelectedRow();
        if (linha >= 0) {
            return listRoteiro.get(linha);
        } else {
            return null;
        }
    }
    
    public void Entrega(){
        roteiro = getItemSelecionado();
        if (roteiro == null) {
            JOptionPane.showMessageDialog(null, "Escolha um roteiro!");
        } else {
            boolean res = roteiroDao.fazerEntrega(roteiro);
            boolean res2 = roteiroDao.fazerEntrega2(roteiro);
            if (res) {
                JOptionPane.showMessageDialog(null, "Objeto Entregue");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            }
        }
        roteiro = null;
    }

    public void pesquisarMAction() {
        String termo = tfPm.getText();
        listRoteiro = roteiroDao.pesquisarM(termo);
        showItensTable();
    }

    public void pesquisarVAction() {
        String termo = tfPv.getText();
        listRoteiro = roteiroDao.pesquisarV(termo);
        showItensTable();
    }

    public void pesquisarOAction() {
        String termo = tfPo.getText();
        listRoteiro = roteiroDao.pesquisarO(termo);
        showItensTable();
    }

}
