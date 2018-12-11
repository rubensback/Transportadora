package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Motorista;
import model.dao.MotoristaDao;

/**
 *
 * @author Rubens Back
 */
public class MotoristaControl {
    //Variáveis do view

    private JTextField tfNome;
    private JTextField tfNasc;
    private JTextField tfEnd;
    private JTextField tfNumcnh;
    private JComboBox cbtipocnh;
    private JTable jtListaM;
    private JTextField tfPesquisa;

    //Veriáveis do model
    private Motorista motorista = null;
    private List<Motorista> listMotorista;
    private MotoristaDao motoristaDao;

    public MotoristaControl(JTextField tfNome, JTextField tfNasc, JTextField tfEnd, JTextField tfNumcnh, JComboBox cbtipocnh, JTable jtListaM, JTextField tfPesquisa) {
        this.tfNome = tfNome;
        this.tfNasc = tfNasc;
        this.tfEnd = tfEnd;
        this.tfNumcnh = tfNumcnh;
        this.cbtipocnh = cbtipocnh;
        this.jtListaM = jtListaM;
        this.tfPesquisa = tfPesquisa;

        listMotorista = new ArrayList();
        motoristaDao = new MotoristaDao();

    }

    public void cadastrarAction() {
        //Montar nova motorista
        motorista = new Motorista();
        motorista.setNome(tfNome.getText());
        motorista.setNascimento(tfNasc.getText());
        motorista.setEndereco(tfEnd.getText());
        motorista.setNumcnh(tfNumcnh.getText());
        motorista.setTipocnh((String) cbtipocnh.getSelectedItem());

        boolean res = motoristaDao.salvar(motorista);
        if (res) {
            JOptionPane.showMessageDialog(null, "Cadastrado!");
            listarAction();
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    public void listarAction() {
        listMotorista = motoristaDao.listar();
        showItensTable();
    }

    private void showItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) jtListaM.getModel();
        model.setNumRows(0);
        for (Motorista m : listMotorista) {
            model.addRow(new Object[]{
                m.getIdm(),
                m.getNome(),
                m.getNascimento(),
                m.getEndereco(),
                m.getTipocnh(),
                m.getNumcnh()
            });
        }
    }

    public void alterarAction() {
        motorista = getItemSelecionado();
        if (motorista == null) {
            JOptionPane.showMessageDialog(null, "Escolha um motorista!");
        } else {
            motorista.setNome(tfNome.getText());
            motorista.setNascimento(tfNasc.getText());
            motorista.setEndereco(tfEnd.getText());
            motorista.setNumcnh(tfNumcnh.getText());
            motorista.setTipocnh((String) cbtipocnh.getSelectedItem()); 

            boolean res = motoristaDao.atualizar(motorista);
            if (res) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso.");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar.");
            }
        }
    }

    public void excluirAction() {
        motorista = getItemSelecionado();
        if (motorista == null) {
            JOptionPane.showMessageDialog(null, "Escolha um motorista!");
        } else {
            boolean res = motoristaDao.excluir(motorista);
            if (res) {
                JOptionPane.showMessageDialog(null, "Motorista excluído!");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            }
        }
        motorista = null;
    }

    private Motorista getItemSelecionado() {
        int linha = jtListaM.getSelectedRow();
        if (linha >= 0) {
            return listMotorista.get(linha);
        } else {
            return null;
        }
    }

    public void pesquisarAction() throws Exception {
        String termo = tfPesquisa.getText();
        listMotorista = motoristaDao.pesquisar(termo);
        showItensTable();
    }

    public void populaTextos() {
        int lin = jtListaM.getSelectedRow();
        String nome = jtListaM.getModel().getValueAt(lin, 1).toString();
        String nasc = jtListaM.getModel().getValueAt(lin, 2).toString();
        String end = jtListaM.getModel().getValueAt(lin, 3).toString();
        String tipo = jtListaM.getModel().getValueAt(lin, 4).toString();
        String num = jtListaM.getModel().getValueAt(lin, 5).toString();
        tfNome.setText(nome);
        tfNasc.setText(nasc);
        tfEnd.setText(end);
        cbtipocnh.setSelectedItem(tipo);
        tfNumcnh.setText(num);
    }
}
