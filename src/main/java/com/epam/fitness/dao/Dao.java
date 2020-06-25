package com.epam.fitness.dao;

import com.epam.fitness.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> getById(int id) throws DaoException;
    List<T> getAll() throws DaoException;
    int save(T item) throws DaoException;
    void update(T item) throws DaoException;
    void removeById(int id) throws DaoException;
}
