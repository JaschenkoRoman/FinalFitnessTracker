package com.epam.fitness.dao;

import com.epam.fitness.builder.Builder;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String TABLE_NAME = "user";
    private static final String GET_ALL = "SELECT * FROM user";
    private static final String GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE " +
            "login = ? AND password = ?";
    private static final String GET_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String GET_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String GET_ALL_BY_ROLE = "SELECT * FROM user WHERE role = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE user SET " +
            "login = ?, password = ?, role = ?, name = ?, surname = ?, " +
            "birthdate = ? WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO user " +
            "(login, password, role, name, surname, birthdate) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public UserDaoImpl(Connection connection, Builder<User> builder) {
        super(connection, builder);
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeQueryForSingleResult(
                GET_BY_LOGIN_AND_PASSWORD,
                login,
                password
                );
    }

    @Override
    public List<User> getAllByRole(Role role) throws DaoException {
        return executeQuery(GET_ALL_BY_ROLE, role.name());
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        return executeQueryForSingleResult(GET_BY_LOGIN, login);
    }

    @Override
    public Optional<User> getById(int id) throws DaoException {
        return executeQueryForSingleResult(GET_BY_ID, id);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(GET_ALL);
    }

    @Override
    public int save(User user) throws DaoException {
        executeUpdate(
                INSERT_USER,
                user.getLogin(),
                user.getPassword(),
                user.getRole().name(),
                user.getName(),
                user.getSurName(),
                user.getBirthDate());
        return getLastInsertId(TABLE_NAME);
    }

    @Override
    public void update(User user) throws DaoException {
        executeUpdate(
                UPDATE_USER,
                user.getLogin(),
                user.getPassword(),
                user.getRole().name(),
                user.getName(),
                user.getSurName(),
                user.getBirthDate(),
                user.getId());
    }

    @Override
    public void removeById(int id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }
}
