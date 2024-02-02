package br.com.ffrantz.dao.generic;

import br.com.ffrantz.entity.Persistente;

import java.io.Serializable;

public class GenericMysqlDAO <T extends Persistente, E extends Serializable> extends GenericDAO<T,E> {

    public GenericMysqlDAO(Class<T> persistenteClass) {
        super(persistenteClass, "Mysql");
    }
}
