package br.com.ffrantz.service.generics;

import br.com.ffrantz.dao.generic.IGenericDAO;
import br.com.ffrantz.entity.Persistente;

import java.io.Serializable;
import java.util.Collection;

public class GenericService<T extends Persistente,E extends Serializable> implements IGenericService<T,E> {

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T,E> dao) {
        this.dao = dao;
    }

    @Override
    public T cadastrar(T entity) {
        return this.dao.cadastrar(entity);
    }

    @Override
    public void excluir(T entity) {
        this.dao.excluir(entity);
    }

    @Override
    public T alterar(T entity) {
        return this.dao.alterar(entity);
    }

    @Override
    public T consultar(E id) {
        return this.dao.consultar(id);
    }

    @Override
    public Collection buscarTodos() {
        return this.dao.buscarTodos();
    }
}
