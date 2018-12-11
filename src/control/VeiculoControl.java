package control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Objeto;
import model.Veiculo;
import model.dao.ObjetoDao;
import model.dao.VeiculoDao;

/**
 *
 * @author Rubens Back
 */
public class VeiculoControl{

    //Variáveis do view
    private JTextField tfMarca;
    private JTextField tfModelo;
    private JTextField tfAno;
    private JTextField tfPlaca;
    private JTextField tfPesquisa;
    private JComboBox cbTipo;
    private JTable jtListaV;

    //Veriáveis do model
    private Veiculo veiculo = null;
    private List<Veiculo> listVeiculo;
    private VeiculoDao veiculoDao;

    public VeiculoControl(JTextField tfMarca, JTextField tfModelo, JTextField tfAno, JTextField tfPlaca, JTextField tfPesquisa, JComboBox cbTipo, JTable jtListaV) {
        this.tfMarca = tfMarca;
        this.tfModelo = tfModelo;
        this.tfAno = tfAno;
        this.tfPlaca = tfPlaca;
        this.tfPesquisa = tfPesquisa;
        this.cbTipo = cbTipo;
        this.jtListaV = jtListaV;

        listVeiculo = new ArrayList();
        veiculoDao = new VeiculoDao();
    }

    public void cadastrarAction() {
        //Montar nova veiculo
        veiculo = new Veiculo();
        veiculo.setMarca(tfMarca.getText());
        veiculo.setModelo(tfModelo.getText());
        veiculo.setAno(Integer.parseInt(tfAno.getText()));
        veiculo.setPlaca(tfPlaca.getText());
        veiculo.setTipo((String) cbTipo.getSelectedItem());
        defineCargaMax(veiculo);

        boolean res = veiculoDao.salvar(veiculo);
        if (res) {
            JOptionPane.showMessageDialog(null, "Cadastrado!");
            listarAction();
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    public void listarAction() {
        listVeiculo = veiculoDao.listar();
        showItensTable();
    }

    private void showItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) jtListaV.getModel();
        model.setNumRows(0);
        for (Veiculo v : listVeiculo) {
            model.addRow(new Object[]{
                v.getIdv(),
                v.getMarca(),
                v.getModelo(),
                v.getAno(),
                v.getPlaca(),
                v.getTipo(),
                v.getCargamax(),});
        }
    }

    public void alterarAction() {
        veiculo = getItemSelecionado();
        if (veiculo == null) {
            JOptionPane.showMessageDialog(null, "Escolha um veiculo!");
        } else {
            veiculo.setMarca(tfMarca.getText());
            veiculo.setModelo(tfModelo.getText());
            veiculo.setAno(Integer.parseInt(tfAno.getText()));
            veiculo.setPlaca(tfPlaca.getText());
            veiculo.setTipo((String) cbTipo.getSelectedItem());
            defineCargaMax(veiculo);

            boolean res = veiculoDao.atualizar(veiculo);
            if (res) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso.");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar.");
            }
        }
    }

    public void excluirAction() {
        veiculo = getItemSelecionado();
        if (veiculo == null) {
            JOptionPane.showMessageDialog(null, "Escolha um veículo!");
        } else {
            boolean res = veiculoDao.excluir(veiculo);
            if (res) {
                JOptionPane.showMessageDialog(null, "Veículo excluído!");
                listarAction();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir!");
            }
        }
        veiculo = null;
    }

    private Veiculo getItemSelecionado() {
        int linha = jtListaV.getSelectedRow();
        if (linha >= 0) {
            return listVeiculo.get(linha);
        } else {
            return null;
        }
    }

    public void pesquisarAction() {
        String termo = tfPesquisa.getText();
        listVeiculo = veiculoDao.pesquisar(termo);
        showItensTable();
    }
    
    public void defineCargaMax(Veiculo tipo){
        if(tipo.getTipo().equals("Van")){
            tipo.setCargamax(1);
        }else if(tipo.getTipo().equals("Caminhão Baú")){
            tipo.setCargamax(3);
        }else if(tipo.getTipo().equals("Carreta")){
            tipo.setCargamax(10);
        }
    }
    
    public void populaTextos(){
        int lin = jtListaV.getSelectedRow();
        String marca = jtListaV.getModel().getValueAt(lin, 1).toString();
        String modelo = jtListaV.getModel().getValueAt(lin, 2).toString();
        String ano = jtListaV.getModel().getValueAt(lin, 3).toString();
        String placa = jtListaV.getModel().getValueAt(lin, 4).toString();
        String tipo = jtListaV.getModel().getValueAt(lin, 5).toString();
        tfMarca.setText(marca);
        tfModelo.setText(modelo);
        tfAno.setText(ano);
        tfPlaca.setText(placa);
        cbTipo.setSelectedItem(tipo);
    }

}
