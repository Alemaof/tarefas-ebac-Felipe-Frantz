package br.com.ffrantz.dao.generic;

import br.com.ffrantz.entity.Persistente;

import java.io.Serializable;

public class GenericPostgreDAO<T extends Persistente, E extends Serializable> extends GenericDAO<T,E> {

    public GenericPostgreDAO(Class<T> persistenteClass) {
        super(persistenteClass, "Postgre");
    }
}
