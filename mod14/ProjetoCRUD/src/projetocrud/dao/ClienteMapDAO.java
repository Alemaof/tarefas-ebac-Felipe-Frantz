/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetocrud.dao;

import java.util.HashMap;
import java.util.Map;
import projetocrud.domain.Cliente;

/**
 *
 * @author alema
 */
public class ClienteMapDAO implements IClienteDAO {
    
    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }

    @Override
    public void cadastrar(Cliente cliente) {
            map.put(cliente.getCpf(), cliente);
    }
  

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = map.get(cliente.getCpf());
        clienteCadastrado.setNome(cliente.getNome());
        clienteCadastrado.setEndereco(cliente.getEndereco());
        clienteCadastrado.setTelefone(cliente.getTelefone());
        clienteCadastrado.setCidade(cliente.getCidade());
        clienteCadastrado.setEstado(cliente.getEstado());
    }

    @Override
    public void excluir(Long cpf) {
        Cliente cliente = map.get(cpf);
        map.remove(cliente.getCpf(),cliente);
    }

    @Override
    public boolean consultar(Long cpf) {
        return map.containsKey(cpf);
   }

    @Override
    public Cliente atribuirCliente(Long cpf) {
        return this.map.get(cpf);
    }
    
  
 
}
