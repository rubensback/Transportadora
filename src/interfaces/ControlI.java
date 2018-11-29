/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Rubens Back
 */
public interface ControlI {
    public void listarAction();
    public void cadastrarAction();
    public void alterarAction();
    public void excluirAction();
    public void popularFormAction();
    public void pesquisarAction();
    
    /**
     * Método que verifica se é para cadastrar ou alterar
     */
    public void salvarAction();
    
}
