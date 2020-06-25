package com.epam.fitness.dao;

import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> getUserByLoginAndPassword(String login, String password) throws DaoException;
    List<User> getAllByRole(Role role) throws DaoException;
    Optional<User> getUserByLogin(String login) throws DaoException;
}
