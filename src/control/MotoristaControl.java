package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

    //Veriáveis do model
    private Motorista motorista = null;
    private List<Motorista> listMotorista;
    private MotoristaDao motoristaDao = new MotoristaDao();

    public MotoristaControl(JTextField tfNome, JTextField tfNasc, JTextField tfEnd, JTextField tfNumcnh, JComboBox cbtipocnh) {
        this.tfNome = tfNome;
        this.tfNasc = tfNasc;
        this.tfEnd = tfEnd;
        this.tfNumcnh = tfNumcnh;
        this.cbtipocnh = cbtipocnh;
    }
    
    
    
    public void cadastrarAction() {
        //Montar nova motorista
        motorista = new Motorista();
        motorista.setNome(tfNome.getText());
        motorista.setNascimento(tfNasc.getText());
        motorista.setEndereco(tfEnd.getText());
        motorista.setNumcnh(tfNumcnh.getText());
        motorista.setTipocnh((String) cbtipocnh.getSelectedItem()); //Conferir a conversão de Obj p/ String
        

        //Salvar motorista no banco e verificar o retorno
        boolean res = motoristaDao.salvar(motorista);
        if (res) {
            JOptionPane.showMessageDialog(null, "Cadastrado!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    public void alterarAction() {
        motorista.setNome(tfNome.getText());
        motorista.setNascimento(tfNasc.getText());
        motorista.setEndereco(tfEnd.getText());
        motorista.setNumcnh(tfNumcnh.getText());
        motorista.setTipocnh((String) cbtipocnh.getSelectedItem()); //Conferir a conversão de Obj p/ String
        
        
        boolean res = motoristaDao.atualizar(motorista);
        if (res) {
            JOptionPane.showMessageDialog(null, "Editado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao editar.");
        }
    }
}
