package br.com.ffrantz.service;

import br.com.ffrantz.dao.IProdutoDAO;

public class ProdutoService {

    IProdutoDAO produtoDAO;

    public ProdutoService (IProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public String salvar(){
        produtoDAO.salvar();
        return "Salvo";
    }

    public String buscar(){
        produtoDAO.buscar();
        return "Encontrado";
    }

    public String atualizar(){
        produtoDAO.atualizar();
        return "Atualizado";
    }

    public String excluir(){
        produtoDAO.excluir();
        return "Excluido";
    }
}
