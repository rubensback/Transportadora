package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Motorista;
import model.Objeto;
import model.Roteiro;
import model.Veiculo;
import model.dao.RoteiroDao;

/**
 *
 * @author Rubens Back
 */
public class RoteiroControl {

    //NÃO FOI ADICIONADO A DATE AINDA
    private JTable tbRoteiro;

    private Roteiro roteiro = null;
    private List<Roteiro> listRoteiro;
    private RoteiroDao roteiroDao;

    public RoteiroControl(JTable tbRoteiro) {
        this.tbRoteiro = tbRoteiro;

        listRoteiro = new ArrayList<>();
        roteiroDao = new RoteiroDao();
    }

    public void listarAction() {
        listRoteiro = roteiroDao.listar();
        showItensTable();
    }

    //Método listar sem data
    private void showItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) tbRoteiro.getModel();
        model.setNumRows(0);
        for (Roteiro r : listRoteiro) {
            model.addRow(new Object[]{
                r.getIdr(),
                r.getMotorista().getIdm(),
                r.getVeiculo().getIdv(),
                r.getObjeto().getIdo()
            });
        }
    }
}
