package br.com.ffrantz.dao;

import br.com.ffrantz.connection.ConnectionFactory;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO extends GenericDAO<Produto, Long> implements IProdutoDAO{

    @Override
    protected void addParamInsert(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setLong(2, produto.getCodigo());
        stm.setString(3, produto.getDescricao());
        stm.setBigDecimal(4, produto.getValor());
        stm.setInt(5, produto.getQuantidade());
    }

    @Override
    protected String getInsertStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_produto (id, nome, codigo, descricao, valor, quantidade) ");
        sb.append("VALUES(nextval('sq_produto'),?,?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected void addParamUpdate(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setLong(2, produto.getCodigo());
        stm.setString(3, produto.getDescricao());
        stm.setBigDecimal(4, produto.getValor());
        stm.setInt(5, produto.getQuantidade());
        stm.setLong(6,produto.getId());
    }

    @Override
    protected String getUpdateStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_produto ");
        sb.append("SET nome = ?, codigo = ?, descricao = ?, valor = ?, quantidade = ? ");
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
        produto.setDescricao(rs.getString("descricao"));
        produto.setValor(rs.getBigDecimal("valor"));
        produto.setQuantidade(rs.getInt("quantidade"));
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

    public Integer addEstoque(Long id, Integer quantidade) throws DAOException {
        Produto prodBuscado = buscarPorID(id);
        prodBuscado.setQuantidade(prodBuscado.getQuantidade() + quantidade);
        return atualizar(prodBuscado);
    }

    public Integer removeEstoque(Long id, Integer quantidade) throws DAOException, MaisDeUmRegistroException {
        Produto prodBuscado = buscarPorID(id);
        prodBuscado.setQuantidade(prodBuscado.getQuantidade() - quantidade);
        return atualizar(prodBuscado);
    }

    public Produto buscarPorID (Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_produto WHERE id = ?";
            stm = connection.prepareStatement(sql);
            addParamSearch(stm,id);
            rs = stm.executeQuery();
            if (rs.next()) {
                produto = insertData(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stm, rs);
        }
        return produto;
    }
}


