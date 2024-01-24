package br.com.ffrantz.dao;

public class ProdutoDAO implements IProdutoDAO {

    @Override
    public String salvar() {
        throw new UnsupportedOperationException("Não funciona sem config de banco");
    }

    @Override
    public String buscar() {
        throw new UnsupportedOperationException("Não funciona sem config de banco");
    }

    @Override
    public String atualizar() {
        throw new UnsupportedOperationException("Não funciona sem config de banco");
    }

    @Override
    public String excluir() {
        throw new UnsupportedOperationException("Não funciona sem config de banco");
    }
}
