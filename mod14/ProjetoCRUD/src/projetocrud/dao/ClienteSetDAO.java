/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projetocrud.dao;

import java.util.HashSet;
import java.util.Set;
import projetocrud.domain.Cliente;

/**
 *
 * @author alema
 */
public class ClienteSetDAO implements IClienteDAO {
    
    private Set<Cliente> set;
    
    public ClienteSetDAO() {
        this.set = new HashSet<>();
    }

    @Override
    public void cadastrar(Cliente cliente) {
        this.set.add(cliente);
    }

    @Override
    public void alterar(Cliente cliente) {
        for (Cliente clienteCadastrado : this.set) {
                if (clienteCadastrado.equals(cliente)) {
                    clienteCadastrado.setNome(cliente.getNome());
                    clienteCadastrado.setTelefone(cliente.getTelefone());
                    clienteCadastrado.setEndereco(cliente.getEndereco());
                    clienteCadastrado.setCidade(cliente.getCidade());
                    clienteCadastrado.setEstado(cliente.getEstado());
                    break;
                }
            }
    }

    @Override
    public void excluir(Long cpf) {
        for (Cliente cliente : this.set) {
            if(cliente.getCpf().equals(cpf))
                set.remove(cliente);
                break;
        }            
    }

    @Override
    public boolean consultar(Long cpf) {
        for (Cliente cliente : this.set) {
             if (cliente.getCpf().equals(cpf)) {
                 return true;
             }
        }
        return false;
    }

    @Override
    public Cliente atribuirCliente(Long cpf) {
        for (Cliente cliente : this.set) {
            if(cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}