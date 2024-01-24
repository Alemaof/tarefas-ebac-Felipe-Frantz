package br.com.ffrantz.service;

import br.com.ffrantz.dao.IClienteDAO;

public class ClienteService {

    IClienteDAO clienteDAO;

    public ClienteService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public String salvar(){
        clienteDAO.salvar();
        return "Salvo";
    }

    public String buscar(){
        clienteDAO.buscar();
        return "Encontrado";
    }

    public String atualizar(){
        clienteDAO.atualizar();
        return "Atualizado";
    }

    public String excluir(){
        clienteDAO.excluir();
        return "Excluido";
    }
}
