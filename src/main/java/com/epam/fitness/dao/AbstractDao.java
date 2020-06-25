package com.epam.fitness.dao;

import com.epam.fitness.builder.Builder;
import com.epam.fitness.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {
    private static final String GET_LAST_INSERT_ID = "SELECT last_insert_id() AS id FROM ";

    private Connection connection;
    private Builder<T> builder;

    public AbstractDao(Connection connection, Builder<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }

    protected int executeUpdate(String query, Object... parameters) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatement(statement, parameters);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected List<T> executeQuery(String query, Object... parameters) throws DaoException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<T> entities = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            prepareStatement(preparedStatement, parameters);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

        }
    }

    protected int getLastInsertId(String tableName) throws DaoException{
        String fullQuery = GET_LAST_INSERT_ID + tableName;
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(fullQuery)) {
                resultSet.next();
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            /*LOGGER.error(e);*/
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeQueryForSingleResult(String query, Object... parameters) throws DaoException {
        List<T> items = executeQuery(query, parameters);
        Optional<T> result = Optional.empty();
        if (items.size() == 1) {
            T firstItem = items.get(0);
            result = Optional.of(firstItem);
        }
        return result;
    }
    protected void prepareStatement(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 1; i < parameters.length + 1; i++) {
            statement.setObject(i, parameters[i - 1]);
        }
    }

}
