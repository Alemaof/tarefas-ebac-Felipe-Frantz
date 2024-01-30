package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;

public interface IProdutoDAO extends IGenericDAO<Produto,Long> {
    Integer addEstoque(Long id, Integer i) throws DAOException;

    Integer removeEstoque(Long produto, Integer quantidade) throws DAOException, MaisDeUmRegistroException;
}
