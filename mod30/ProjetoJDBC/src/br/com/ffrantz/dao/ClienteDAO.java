package br.com.ffrantz.dao;

import br.com.ffrantz.connection.ConnectionFactory;
import br.com.ffrantz.dao.generic.GenericDAO;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Persistente;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends GenericDAO<Cliente,Long> implements IClienteDAO{

    @Override
    protected void addParamInsert(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setLong(2, cliente.getCpf());
        stm.setInt(3, cliente.getIdade());
        stm.setLong(4, cliente.getTelefone());
        stm.setString(5, cliente.getEndereco());
        stm.setString(6, cliente.getCidade());
        stm.setString(7, cliente.getEstado());
    }

    @Override
    protected String getInsertStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_cliente (id, nome, cpf, idade, telefone, endereco, cidade, estado) ");
        sb.append("VALUES(nextval('sq_cliente'),?,?,?,?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected void addParamUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setLong(2,cliente.getCpf());
        stm.setInt(3, cliente.getIdade());
        stm.setLong(4, cliente.getTelefone());
        stm.setString(5, cliente.getEndereco());
        stm.setString(6, cliente.getCidade());
        stm.setString(7, cliente.getEstado());
        stm.setLong(8,cliente.getId());
    }

    @Override
    protected String getUpdateStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_cliente ");
        sb.append("SET nome = ?, cpf = ?, idade = ?, telefone = ?, endereco= ?, cidade = ?, estado = ? ");
        sb.append("WHERE id = ?");
        return sb.toString();
    }

    @Override
    protected void addParamDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setLong(1, cliente.getCpf());
    }

    @Override
    protected String getDeleteParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_cliente ");
        sb.append("WHERE cpf = ?");
        return sb.toString();
    }

    @Override
    protected Cliente insertData(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getLong("cpf"));
        cliente.setIdade(rs.getInt("idade"));
        cliente.setTelefone(rs.getLong("telefone"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setCidade(rs.getString("cidade"));
        cliente.setEstado(rs.getString("estado"));
        return cliente;
    }

    @Override
    protected void addParamSearch(PreparedStatement stm, Long codigo) throws SQLException {
        stm.setLong(1,codigo);
    }

    @Override
    protected String getSearchParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_cliente ");
        sb.append("WHERE cpf = ?");
        return sb.toString();
    }

    @Override
    protected String getSearchAllParam() {
        return "SELECT * FROM tb_cliente";
    }
}
