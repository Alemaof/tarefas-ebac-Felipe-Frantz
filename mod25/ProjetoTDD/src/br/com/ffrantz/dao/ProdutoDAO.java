package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Produto;

public class ProdutoDAO extends GenericDAO<Produto, String> {

    public ProdutoDAO() {
        super();
    }
    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualizarDados(Produto entity, Produto entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setDescricao(entity.getDescricao());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setValor(entity.getValor());
    }
}
