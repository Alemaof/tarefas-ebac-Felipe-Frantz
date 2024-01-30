package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.domain.Venda;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;

import java.sql.SQLException;


public interface IVendaDAO extends IGenericDAO<Venda,Long> {

    void adicionarProduto(Produto produto, Integer quantidade, Venda venda) throws DAOException, MaisDeUmRegistroException, SQLException;

    void cancelarVenda(Venda venda) throws DAOException;

    Integer getQuantidadeTotalProdutos();

    void removerProduto(Produto prod, Integer i, Venda vendaConsultada) throws DAOException;

    void removerTodosProdutos(Venda vendaConsultada) throws DAOException;

    void finalizarVenda(Venda venda) throws DAOException;
}
