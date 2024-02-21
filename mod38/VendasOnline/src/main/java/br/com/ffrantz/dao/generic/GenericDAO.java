package br.com.ffrantz.dao.generic;

import br.com.ffrantz.entity.Persistente;
import javax.persistence.EntityManager;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceContext;

public class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E>{
	
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> persistenteClass;

    public GenericDAO(Class<T> persistenteClass) {
        this.persistenteClass = persistenteClass;
    }


    @Override
    public T cadastrar(T entity) {
        entityManager.persist(entity);
        return entity;
    }


    @Override
    public void excluir(T entity) {
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
    }

    @Override
    public T alterar(T entity) {
        entity = entityManager.merge(entity);
        return entity;
    }

    @Override
    public T consultar(E id) {
        T entity = entityManager.find(this.persistenteClass, id);
        return entity;
    }

    @Override
    public Collection<T> buscarTodos() {
        List<T> list =
                entityManager.createQuery(getSelectSql(), this.persistenteClass).getResultList();
        return list;
    }

    private String getSelectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistenteClass.getSimpleName());
        sb.append(" obj");
        return sb.toString();
    }
}
