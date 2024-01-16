/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projetocrud.dao;

import projetocrud.domain.Cliente;

/**
 *
 * @author alema
 */
public interface IClienteDAO {
    
    public void cadastrar(Cliente cliente);
    
    public void alterar(Cliente cliente);
    
    public void excluir(Long cpf);
    
    public boolean consultar(Long cpf);
    
    public Cliente atribuirCliente(Long cpf);
    
}
