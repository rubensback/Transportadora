package control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Objeto;
import model.dao.ObjetoDao;

/**
 *
 * @author Rubens Back
 */
public class ObjetoControl{

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy");

    //Variáveis do view
    private JTextField tfNomerem;
    private JTextField tfEndrem;
    private JTextField tfNomedest;
    private JTextField tfEnddest;
    private JTextField tfData;
    private JTextField tfPeso;
    private JTextField tfPesquisa;
    private JTable jtListaO;

    //Veriáveis do model
    private Objeto objeto = null;
    private List<Objeto> listObjeto;
    private ObjetoDao objetoDao;

    public ObjetoControl(JTextField tfNomerem, JTextField tfEndrem, JTextField tfNomedest, JTextField tfEnddest, JTextField tfData, JTextField tfPeso, JTextField tfPesquisa, JTable jtListaO) {
        this.tfNomerem = tfNomerem;
        this.tfEndrem = tfEndrem;
        this.tfNomedest = tfNomedest;
        this.tfEnddest = tfEnddest;
        this.tfData = tfData;
        this.tfPeso = tfPeso;
        this.tfPesquisa = tfPesquisa;
        this.jtListaO = jtListaO;

        listObjeto = new ArrayList();
        objetoDao = new ObjetoDao();
    }

    public void cadastrarAction() throws ParseException {
        //Montar nova objeto
        objeto = new Objeto();
        objeto.setNomerem(tfNomerem.getText());
        objeto.setEnderecorem(tfEndrem.getText());
        objeto.setNomedest(tfNomedest.getText());
        objeto.setEnderecodest(tfEnddest.getText());
        objeto.setDatadep(sdf1.parse(tfData.getText()));
        objeto.setPeso(Double.parseDouble(tfPeso.getText()));

        boolean res = objetoDao.salvar(objeto);
        if (res) {
            JOptionPane.showMessageDialog(null, "Cadastrado!");
            listarAction();
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    public void listarAction() {
        listObjeto = objetoDao.listar();
        showItensTable();
    }

    private void showItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) jtListaO.getModel();
        model.setNumRows(0);
        for (Objeto o : listObjeto) {
            model.addRow(new Object[]{
                o.getIdo(),
                o.getNomerem(),
                o.getEnderecorem(),
                o.getNomedest(),
                o.getEnderecodest(),
                o.getDatadep(),
                o.getPeso()
            });
        }
    }

    public void alterarAction() throws ParseException {
        objeto = getItemSelecionado();
        if (objeto == null) {
            JOptionPane.showMessageDialog(null, "Escolha um objeto!");
        } else {
            objeto.setNomerem(tfNomerem.getText());
            objeto.setEnderecorem(tfEndrem.getText());
            objeto.setNomedest(tfNomedest.getText());
            objeto.setEnderecodest(tfEnddest.getText());
            objeto.setDatadep(sdf1.parse(tfData.getText()));
            objeto.setPeso(Double.parseDouble(tfPeso.getText()));

            boolean res = objetoDao.atualizar(objeto);
            if (res) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso.");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar.");
            }
        }
    }

    public void excluirAction() {
        objeto = getItemSelecionado();
        if (objeto == null) {
            JOptionPane.showMessageDialog(null, "Escolha um objeto!");
        } else {
            boolean res = objetoDao.excluir(objeto);
            if (res) {
                JOptionPane.showMessageDialog(null, "Objeto excluído!");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            }
        }
        objeto = null;
    }

    private Objeto getItemSelecionado() {
        int linha = jtListaO.getSelectedRow();
        if (linha >= 0) {
            return listObjeto.get(linha);
        } else {
            return null;
        }
    }

    public void pesquisarAction() {
        String termo = tfPesquisa.getText();
        listObjeto = objetoDao.pesquisar(termo);
        showItensTable();
    }
    
    public void populaTextos(){
        int lin = jtListaO.getSelectedRow();
        String nr = jtListaO.getModel().getValueAt(lin, 1).toString();
        String er = jtListaO.getModel().getValueAt(lin, 2).toString();
        String nd = jtListaO.getModel().getValueAt(lin, 3).toString();
        String ed = jtListaO.getModel().getValueAt(lin, 4).toString();
        String data = jtListaO.getModel().getValueAt(lin, 5).toString();
        String peso = jtListaO.getModel().getValueAt(lin, 6).toString();
        tfNomerem.setText(nr);
        tfEndrem.setText(er);
        tfNomedest.setText(nd);
        tfEnddest.setText(ed);
        tfData.setText(data);
        tfPeso.setText(peso);
    }

}
