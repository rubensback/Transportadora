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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Motorista;
import model.Objeto;
import model.Roteiro;
import model.Veiculo;
import model.dao.GerarRoteiroDao;
import model.dao.MotoristaDao;
import model.dao.ObjetoDao;
import model.dao.VeiculoDao;

/**
 *
 * @author Rubens Back
 */
public class GerarRoteiroControl {

    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yy");

    private JTable tbMotorista;
    private JTable tbVeiculo;
    private JTable tbObjeto;
    private JTextField tfIdr;
    private JTextField tfDatar;
    private JComboBox<Roteiro> cbRoteiro; // talvez precise ser <Roteiro>

    private Roteiro roteiro = null;
    private Motorista motorista = null;
    private Veiculo veiculo = null;
    private Objeto objeto = null;

    private List<Motorista> listMotorista;
    private List<Veiculo> listVeiculo;
    private List<Objeto> listObjeto;

    private GerarRoteiroDao grDao;
    private MotoristaDao motoristaDao;
    private VeiculoDao veiculoDao;
    private ObjetoDao objetoDao;

    public GerarRoteiroControl(JTable tbMotorista, JTable tbVeiculo, JTable tbObjeto, JTextField tfIdr, JTextField tfDatar, JComboBox<Roteiro> cbRoteiro) {
        this.tbMotorista = tbMotorista;
        this.tbVeiculo = tbVeiculo;
        this.tbObjeto = tbObjeto;
        this.tfIdr = tfIdr;
        this.tfDatar = tfDatar;
        this.cbRoteiro = cbRoteiro;

        listMotorista = new ArrayList<>();
        listVeiculo = new ArrayList<>();
        listObjeto = new ArrayList<>();
        motoristaDao = new MotoristaDao();
        veiculoDao = new VeiculoDao();
        objetoDao = new ObjetoDao();
        grDao = new GerarRoteiroDao();
    }

    /*
    //preenche o objeto...
        // Suponhamos que você tenha um dataTable na sua página onde guarda os registros
        // dos contatos dos clientes. Então, você pega os valores desse dataTable e adiciona
        // à coleção de contatos dos clientes. É só um exemplo, mas você faz do seu jeito:
        for (Contato contatoEntity : request.getParameter("CONTATOS")) {
            entityCliente.getContatos().add(contatoEntity);
        }
        // Se você tá adicionando o cliente junto, você chama o [b]create[/b] do DAOCliente.
        daoCliente.create(entityCliente);
        for (Contato contatoEntity : daoCliente.getContatos()) {
            contatoEntity.setCliente(entityCliente.getID()); //<-- referencia o ID do cliente no objeto.
            daoContato.create(contato); // <-- adiciona o contato à tabela.
        }*/
    public void cadastrarAction() throws ParseException {
        //Montar novo objeto

        roteiro = new Roteiro();
        roteiro.setIdr(Integer.parseInt(tfIdr.getText()));
        roteiro.setDatar(sdf1.parse(tfDatar.getText()));
        roteiro.setMotorista((Motorista) getMSelecionado());
        roteiro.setVeiculo((Veiculo) getVSelecionado());
        roteiro.setObjeto((Objeto) getOSelecionado());
        /*if ((tfIdr.getText() == null) && (tfDatar.getText()) == null) {
            for (int i = 0; i < cbRoteiro.getModel().getSize(); i++) {
                Roteiro r = cbRoteiro.getModel().getElementAt(i);
                if (roteiro.getIdr() == r.getIdr()) {
                    cbRoteiro.setSelectedIndex(i);
                    break;
                }
            }
            roteiro.setIdr(cbRoteiro.getSelectedIndex());
        }*/
    

        boolean res = grDao.salvar(roteiro);
        if (res) {
            JOptionPane.showMessageDialog(null, "Cadastrado!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    public void PopularCbRoteiroAction() {
        cbRoteiro.removeAllItems();
        for (Roteiro r : grDao.listar()) {
            cbRoteiro.addItem(r);
        }
    }

    private Motorista getMSelecionado() {
        int linhaM = tbMotorista.getSelectedRow();
        if (linhaM >= 0) {
            return listMotorista.get(linhaM);
        } else {
            return null;
        }
    }

    private Veiculo getVSelecionado() {
        int linhaV = tbVeiculo.getSelectedRow();
        if (linhaV >= 0) {
            return listVeiculo.get(linhaV);
        } else {
            return null;
        }
    }

    private Objeto getOSelecionado() {
        int linhaO = tbMotorista.getSelectedRow();
        if (linhaO >= 0) {
            return listObjeto.get(linhaO);
        } else {
            return null;
        }
    }

    public void listarMotoristaAction() {
        listMotorista = motoristaDao.listar();
        showMotoristaItensTable();
    }

    private void showMotoristaItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) tbMotorista.getModel();
        model.setNumRows(0);
        for (Motorista m : listMotorista) {
            model.addRow(new Object[]{
                m.getIdm(),
                m.getNome(),
                m.getTipocnh()
            });
        }
    }

    public void listarVeiculoAction() {
        listVeiculo = veiculoDao.listar();
        showVeiculoItensTable();
    }

    private void showVeiculoItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) tbVeiculo.getModel();
        model.setNumRows(0);
        for (Veiculo v : listVeiculo) {
            model.addRow(new Object[]{
                v.getIdv(),
                v.getTipo(),
                v.getCargamax()
            });
        }
    }

    public void listarObjetoAction() {
        listObjeto = objetoDao.listar();
        showObjetoItensTable();
    }

    private void showObjetoItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) tbObjeto.getModel();
        model.setNumRows(0);
        for (Objeto o : listObjeto) {
            model.addRow(new Object[]{
                o.getIdo(),
                o.getDatadep()
            });
        }
    }
}
