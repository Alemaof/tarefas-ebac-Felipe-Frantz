package br.com.ffrantz.dao.generic;

import br.com.ffrantz.connection.ConnectionFactory;
import br.com.ffrantz.domain.Cliente;
import br.com.ffrantz.domain.Persistente;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E> {

    protected abstract void addParamInsert(PreparedStatement stm, T entity) throws SQLException;

    protected abstract String getInsertStatement();

    protected abstract void addParamUpdate(PreparedStatement stm, T entity) throws SQLException;

    protected abstract String getUpdateStatement();

    protected abstract void addParamDelete(PreparedStatement stm, T entity) throws SQLException;

    protected abstract String getDeleteParam();

    protected abstract T insertData(ResultSet rs) throws SQLException;

    protected abstract void addParamSearch(PreparedStatement stm, E codigo) throws SQLException;

    protected abstract String getSearchParam();

    protected abstract String getSearchAllParam();

    @Override
    public Integer salvar(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getInsertStatement();
            stm = connection.prepareStatement(sql);
            addParamInsert(stm, entity);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }


    @Override
    public Integer atualizar(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getUpdateStatement();
            stm = connection.prepareStatement(sql);
            addParamUpdate(stm, entity);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        finally {
            ConnectionFactory.closeConnection(connection, stm, null);
        }
    }


    @Override
    public Integer excluir(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getDeleteParam();
            stm = connection.prepareStatement(sql);
            addParamDelete(stm, entity);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeConnection(connection, stm, null);
        }
    }

    @Override
    public T buscar(E codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        T entity = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSearchParam();
            stm = connection.prepareStatement(sql);
            addParamSearch(stm,codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                entity = insertData(rs);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeConnection(connection, stm, rs);
        }
        return entity;
    }

    @Override
    public List<T> buscarTodos() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        T entity = null;
        List<T> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSearchAllParam();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                entity = insertData(rs);
                list.add(entity);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeConnection(connection, stm, rs);
        }
        return list;
    }
}
