package br.com.ffrantz.dao.generic;

import br.com.ffrantz.entity.Persistente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;

public class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E>{
    @Override
    public T cadastrar(T entity) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ProjetoJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return entity;
    }
}
