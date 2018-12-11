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
import model.RoteiroData;
import model.RoteiroLista;
import model.Veiculo;
import model.dao.GerarRoteiroDao;
import model.dao.MotoristaDao;
import model.dao.ObjetoDao;
import model.dao.RoteiroDataDao;
import model.dao.VeiculoDao;

/**
 *
 * @author Rubens Back
 */
public class GerarRoteiroControl {


    private JComboBox<RoteiroData> cbRoteiroData;
    private JComboBox<Motorista> cbMotorista;
    private JComboBox<Veiculo> cbVeiculo;
    private JComboBox<Objeto> cbObjeto;

    private RoteiroLista roteiro = null;
    private Motorista motorista = null;
    private Veiculo veiculo = null;
    private Objeto objeto = null;

    private List<RoteiroData> listRoteiroData;
    private List<Motorista> listMotorista;
    private List<Veiculo> listVeiculo;
    private List<Objeto> listObjeto;

    private GerarRoteiroDao grDao;
    private RoteiroDataDao roteiroDataDao;
    private MotoristaDao motoristaDao;
    private VeiculoDao veiculoDao;
    private ObjetoDao objetoDao;

    public GerarRoteiroControl() {
    }

    public GerarRoteiroControl(JComboBox cbRoteiroData, JComboBox cbMotorista, JComboBox cbVeiculo, JComboBox cbObjeto) {
        this.cbRoteiroData = cbRoteiroData;
        this.cbMotorista = cbMotorista;
        this.cbVeiculo = cbVeiculo;
        this.cbObjeto = cbObjeto;

        listRoteiroData = new ArrayList<>();
        listMotorista = new ArrayList<>();
        listVeiculo = new ArrayList<>();
        listObjeto = new ArrayList<>();
        motoristaDao = new MotoristaDao();
        veiculoDao = new VeiculoDao();
        objetoDao = new ObjetoDao();
        grDao = new GerarRoteiroDao();
        roteiroDataDao = new RoteiroDataDao();
        //rControl = new RoteiroControl();
        
    }

    public void cadastrarComboAction() throws Exception {
        //Montar novo objeto
        boolean res = false;
        roteiro = new RoteiroLista();
        roteiro.setRoteiroData((RoteiroData) cbRoteiroData.getSelectedItem());
        roteiro.setMotorista((Motorista) cbMotorista.getSelectedItem());
        roteiro.setVeiculo((Veiculo) cbVeiculo.getSelectedItem());
        roteiro.setObjeto((Objeto) cbObjeto.getSelectedItem());
        
        if(roteiro.getVeiculo().getTipo().equals("Van")){
            if((roteiro.getMotorista().getTipocnh().equals("B")) || (roteiro.getMotorista().getTipocnh().equals("C")) || (roteiro.getMotorista().getTipocnh().equals("BC"))){
                res = grDao.salvar(roteiro);
            }                
        }else if((roteiro.getVeiculo().getTipo().equals("Caminhão Baú")) || ((roteiro.getVeiculo().getTipo().equals("Carreta")))){
            if(roteiro.getMotorista().getTipocnh().equals("C") || (roteiro.getMotorista().getTipocnh().equals("BC"))){
                res = grDao.salvar(roteiro);
            }
        }else{
            System.out.println("Este motorista não pode dirigir este veículo");
        }
        
        if (res) {
            JOptionPane.showMessageDialog(null, "Gerado!");
            
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    public void mostrarCombos() {
        cbRoteiroData.removeAllItems();
        for (RoteiroData r : roteiroDataDao.listar()) {
            cbRoteiroData.addItem(r);
        }        
        cbMotorista.removeAllItems();
        for (Motorista m : motoristaDao.listar()) {
            cbMotorista.addItem(m);
        }        
        cbVeiculo.removeAllItems();
        for (Veiculo v : veiculoDao.listar()) {
            cbVeiculo.addItem(v);
        }        
        cbObjeto.removeAllItems();
        for (Objeto o : objetoDao.listar()) {
            cbObjeto.addItem(o);
        }
    }

}
