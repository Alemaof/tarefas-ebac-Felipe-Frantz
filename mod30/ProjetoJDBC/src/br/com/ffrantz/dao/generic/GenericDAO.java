package br.com.ffrantz.dao.generic;

import br.com.ffrantz.connection.ConnectionFactory;
import br.com.ffrantz.domain.Persistente;
import br.com.ffrantz.exceptions.DAOException;
import br.com.ffrantz.exceptions.MaisDeUmRegistroException;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public Integer salvar(T entity) throws DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getInsertStatement();
            stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            addParamInsert(stm, entity);
            int rowsAffected = stm.executeUpdate();

            if(rowsAffected > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()){
                    if (rs.next()) {
                        entity.setId(rs.getLong(1));
                    }
                }
            }
            return rowsAffected;
        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        }
    }


    @Override
    public Integer atualizar(T entity) throws DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getUpdateStatement();
            stm = connection.prepareStatement(sql);
            addParamUpdate(stm, entity);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        }
        finally {
            ConnectionFactory.closeConnection(connection, stm, null);
        }
    }


    @Override
    public Integer excluir(T entity) throws DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getDeleteParam();
            stm = connection.prepareStatement(sql);
            addParamDelete(stm, entity);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stm, null);
        }
    }

    @Override
    public T buscar(E codigo) throws DAOException, MaisDeUmRegistroException {
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
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stm, rs);
        }
        return entity;
    }

    @Override
    public List<T> buscarTodos() throws DAOException {
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
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stm, rs);
        }
        return list;
    }
}
