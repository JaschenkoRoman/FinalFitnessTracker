package com.epam.fitness.service;

import com.epam.fitness.dao.UserDao;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User> {
    private UserDao userDao;

    public UserService(UserDao UserDao) {
        this.userDao = UserDao;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.getUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    public List<User> getUsersByRole(Role role) throws ServiceException {
        try {
            return userDao.getAllByRole(role);
        } catch (DaoException e) {
            throw  new ServiceException(e);
        }
    }
    
    public Optional<User> getByLogin(String login) throws ServiceException {
        try {
            return userDao.getUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public Optional<User> getById(int id) throws ServiceException {
        try {
            return userDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public int save(User user) throws ServiceException {
        try {
            return userDao.save(user);
        } catch (DaoException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public void removeById(int id) throws ServiceException {
        try {
            userDao.removeById(id);
        } catch (DaoException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw  new ServiceException(e);
        }
    }
}
