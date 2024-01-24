package br.com.ffrantz.service;

import br.com.ffrantz.domain.Persistente;
import br.com.ffrantz.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;

public interface IGenericService <T extends Persistente, E extends Serializable> {

    public Boolean salvar(T entity) throws TipoChaveNaoEncontradaException;

    public void excluir(E valor);

    public void alterar(T entity) throws TipoChaveNaoEncontradaException;

    public T buscarPorChave(E valor);
}
