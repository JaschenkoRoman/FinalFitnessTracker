package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    Optional<T> getById(int id) throws ServiceException;

    List<T> getAll() throws ServiceException;

    int save(T item) throws ServiceException;

    void removeById(int id) throws ServiceException;

    void update(T item) throws ServiceException;
}
