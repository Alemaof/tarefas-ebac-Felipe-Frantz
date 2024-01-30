package br.com.ffrantz.dao.generic;

import br.com.ffrantz.domain.Persistente;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T extends Persistente, E extends Serializable> {

    public Integer salvar(T entity) throws DAOException;

    public Integer atualizar(T entity) throws DAOException;

    public Integer excluir(T entity) throws DAOException;

    public T buscar(E codigo) throws DAOException, MaisDeUmRegistroException;

    public List<T> buscarTodos() throws DAOException;

}
