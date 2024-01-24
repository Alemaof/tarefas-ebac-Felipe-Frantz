package br.com.ffrantz.service;

import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.domain.Persistente;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;

public class GenericService<T extends Persistente, E extends Serializable> implements IGenericService<T,E>  {

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T,E> dao) {
        this.dao = dao;
    }

    @Override
    public Boolean salvar(T entity) throws TipoChaveNaoEncontradaException {
        return dao.salvar(entity);
    }

    @Override
    public void excluir(E valor) {
        dao.excluir(valor);
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        dao.alterar(entity);
    }

    @Override
    public T buscarPorChave(E valor) {
        return dao.buscarPorChave(valor);
    }
}
