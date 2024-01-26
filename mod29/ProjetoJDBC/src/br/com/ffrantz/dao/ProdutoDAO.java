package br.com.ffrantz.dao;

import br.com.ffrantz.connection.ConnectionFactory;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends GenericDAO<Produto, Long> {

    @Override
    protected void addParamInsert(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setLong(2, produto.getCodigo());
    }

    @Override
    protected String getInsertStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_produto (id, nome, codigo) ");
        sb.append("VALUES(nextval('sq_produto'),?,?)");
        return sb.toString();
    }

    @Override
    protected void addParamUpdate(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setLong(2, produto.getCodigo());
        stm.setLong(3,produto.getId());
    }

    @Override
    protected String getUpdateStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_produto ");
        sb.append("SET nome = ?, codigo = ? ");
        sb.append("WHERE id = ?");
        return sb.toString();
    }

    @Override
    protected void addParamDelete(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setLong(1, produto.getCodigo());
    }

    @Override
    protected String getDeleteParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_produto ");
        sb.append("WHERE codigo = ?");
        return sb.toString();
    }

    @Override
    protected Produto insertData(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getLong("id"));
        produto.setNome(rs.getString("nome"));
        produto.setCodigo(rs.getLong("codigo"));
        return produto;
    }

    @Override
    protected void addParamSearch(PreparedStatement stm, Long codigo) throws SQLException {
        stm.setLong(1,codigo);
    }

    @Override
    protected String getSearchParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produto ");
        sb.append("WHERE codigo = ?");
        return sb.toString();
    }

    @Override
    protected String getSearchAllParam() {
        return "SELECT * FROM tb_produto";
    }
}
