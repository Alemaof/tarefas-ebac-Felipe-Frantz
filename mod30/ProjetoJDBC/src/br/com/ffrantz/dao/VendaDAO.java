package br.com.ffrantz.dao;

import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Produto;
import br.com.ffrantz.domain.ProdutoVendido;
import br.com.ffrantz.domain.Venda;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;
import br.com.ffrantz.factory.ClienteFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static br.com.ffrantz.connection.ConnectionFactory.closeConnection;
import static br.com.ffrantz.connection.ConnectionFactory.getConnection;

public class VendaDAO extends GenericDAO<Venda, Long> implements IVendaDAO {

    private Set<ProdutoVendido> produtos = new HashSet<>();

    private ProdutoVendidoDAO produtoVendidoDAO;

    private IProdutoDAO produtoDAO;

    @Override
    public void adicionarProduto(Produto produto, Integer quantidade, Venda venda) {
        validarStatus(venda);
        Optional<ProdutoVendido> op =
                produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
        if (op.isPresent()) {
            ProdutoVendido produtoQtd = op.get();
            produtoQtd.setQuantidade(produtoQtd.getQuantidade() + quantidade);
            produtoQtd.setValorTotal(produto.getValor().multiply(BigDecimal.valueOf(produtoQtd.getQuantidade())));
            produtos.remove(produtoQtd);
            produtos.add(produtoQtd);
            venda.setProdutoVendido(produtos);
        } else {
            ProdutoVendido prod = new ProdutoVendido();
            prod.setProduto(produto);
            prod.setQuantidade(quantidade);
            prod.setValorTotal(produto.getValor().multiply(BigDecimal.valueOf(quantidade)));
            produtos.add(prod);
            venda.setProdutoVendido(produtos);
        }
        recalcularValorTotalVenda(venda);
    }

    private void validarStatus(Venda venda) {
        if (venda.getStatus() == Venda.Status.CONCLUIDA) {
            throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
        }
    }

    public void removerProduto(Produto produto, Integer quantidade, Venda vendaConsultada) {
        validarStatus(vendaConsultada);
        Optional<ProdutoVendido> op =
                produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

        if (op.isPresent()) {
            ProdutoVendido produtoQtd = op.get();
            if (produtoQtd.getQuantidade()>quantidade) {
                produtoQtd.setQuantidade(produtoQtd.getQuantidade() - quantidade);
                produtoQtd.setValorTotal(produto.getValor().multiply(BigDecimal.valueOf(produtoQtd.getQuantidade())));
                recalcularValorTotalVenda(vendaConsultada);
            } else {
                produtos.remove(op.get());
                recalcularValorTotalVenda(vendaConsultada);
            }

        }
    }

    public void removerTodosProdutos(Venda venda) {
        validarStatus(venda);
        produtos.clear();
        venda.setValorTotal(BigDecimal.ZERO);
    }

    public Integer getQuantidadeTotalProdutos() {
        int result = produtos.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantidade(), Integer::sum);
        return result;
    }

    public void recalcularValorTotalVenda(Venda venda) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoVendido prod : venda.getProdutoVendido()) {
            valorTotal = valorTotal.add(prod.getValorTotal());
        }
        venda.setValorTotal(valorTotal);
    }

    public void finalizarVenda(Venda venda) throws DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement("UPDATE TB_VENDA SET STATUS_VENDA = ? WHERE ID = ?");
            stm.setString(1, Venda.Status.CONCLUIDA.name());
            stm.setLong(2, venda.getId());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    public void cancelarVenda(Venda venda) throws DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement("UPDATE TB_VENDA SET STATUS_VENDA = ? WHERE ID = ?");
            stm.setString(1, Venda.Status.CANCELADA.name());
            stm.setLong(2, venda.getId());
            stm.executeUpdate();
            produtoVendidoDAO = new ProdutoVendidoDAO();
            Map<Long,Integer> prods = produtoVendidoDAO.searchAll(venda.getId());
            produtoDAO = new ProdutoDAO();
            for (Map.Entry<Long,Integer> prod : prods.entrySet()) {
                produtoDAO.addEstoque(prod.getKey(),prod.getValue());
            }

        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    protected void addParamInsert(PreparedStatement stm, Venda venda) throws SQLException {
        stm.setLong(1, venda.getCodigo());
        stm.setLong(2, venda.getCliente().getId());
        stm.setBigDecimal(3, venda.getValorTotal());
        stm.setTimestamp(4, Timestamp.from(venda.getDataDaVenda()));
        stm.setString(5, venda.getStatus().name());
    }

    @Override
    protected String getInsertStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_VENDA ");
        sb.append("(ID, CODIGO, ID_CLIENTE_FK, VALOR_TOTAL, DATA_VENDA, STATUS_VENDA)");
        sb.append("VALUES (nextval('sq_venda'),?,?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected void addParamUpdate(PreparedStatement stm, Venda entity) throws SQLException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    protected String getUpdateStatement() {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    protected void addParamDelete(PreparedStatement stm, Venda entity) throws SQLException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    protected String getDeleteParam() {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    protected Venda insertData(ResultSet rs) throws SQLException {
        //TODO
        Cliente cliente = new Cliente();
        Venda venda1 = new Venda();
        venda1.setCliente(ClienteFactory.convert(rs));
        venda1.setId(rs.getLong("ID_VENDA"));
        venda1.setCodigo(rs.getLong("CODIGO"));
        venda1.setValorTotal(rs.getBigDecimal("VALOR_TOTAL"));
        venda1.setDataDaVenda(rs.getTimestamp("DATA_VENDA").toInstant());
        venda1.setStatus(Venda.Status.getByName(rs.getString("STATUS_VENDA")));
        return venda1;
    }

    @Override
    protected void addParamSearch(PreparedStatement stm, Long codigo) throws SQLException {
        stm.setLong(1,codigo);
    }

    @Override
    protected String getSearchParam() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSearchAllParam());
        sb.append("WHERE V.CODIGO = ? ");
        return sb.toString();
    }

    @Override
    protected String getSearchAllParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT V.ID AS ID_VENDA, V.CODIGO, V.VALOR_TOTAL, V.DATA_VENDA, V.STATUS_VENDA, ");
        sb.append("C.ID AS ID_CLIENTE, C.NOME, C.CPF, C.IDADE, C.TELEFONE, C.ENDERECO, C.CIDADE, C.ESTADO ");
        sb.append("FROM TB_VENDA V ");
        sb.append("INNER JOIN TB_CLIENTE C ON V.ID_CLIENTE_FK = C.ID ");
        return sb.toString();
    }

    @Override
    public Integer salvar(Venda venda) throws DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(getInsertStatement(), Statement.RETURN_GENERATED_KEYS);
            addParamInsert(stm, venda);
            int rowsAffected = stm.executeUpdate();

            if(rowsAffected > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()){
                    if (rs.next()) {
                        venda.setId(rs.getLong(1));
                    }
                }
                for (ProdutoVendido prod : venda.getProdutoVendido()) {
                    stm = connection.prepareStatement(getProdInsetParam());
                    addProdParamInsert(stm, venda, prod);
                    stm.executeUpdate();
                    produtoDAO = new ProdutoDAO();
                    produtoDAO.removeEstoque(prod.getProduto(),prod.getQuantidade());
                }
            }
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        } catch (MaisDeUmRegistroException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    private void addProdParamInsert(PreparedStatement stm, Venda venda, ProdutoVendido prod) throws SQLException {
        stm.setLong(1, prod.getProduto().getId());
        stm.setLong(2, venda.getId());
        stm.setInt(3, prod.getQuantidade());
        stm.setBigDecimal(4, prod.getValorTotal());
    }

    private String getProdInsetParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_PRODUTOSVENDIDOS ");
        sb.append("(ID, ID_PRODUTO_FK, ID_VENDA_FK, QUANTIDADE, VALOR_TOTAL)");
        sb.append("VALUES (nextval('SQ_PRODUTOSVENDIDOS'),?,?,?,?)");
        return sb.toString();
    }
}
