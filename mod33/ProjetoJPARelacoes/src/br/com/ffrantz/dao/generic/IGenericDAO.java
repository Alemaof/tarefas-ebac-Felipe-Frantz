package br.com.ffrantz.dao.generic;

import br.com.ffrantz.entity.Persistente;

import java.io.Serializable;

public interface IGenericDAO<T extends Persistente,E extends Serializable> {

    public T cadastrar (T entity);
}
