package br.com.ffrantz.dao.generic;

import br.com.ffrantz.domain.Persistente;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;

public interface IGenericDAO <T extends Persistente, E extends Serializable> {

    public Boolean salvar(T entity) throws TipoChaveNaoEncontradaException;

    public T buscarPorChave(E valor);

    public void excluir(E valor);

    public void alterar(T entity) throws TipoChaveNaoEncontradaException;
}
