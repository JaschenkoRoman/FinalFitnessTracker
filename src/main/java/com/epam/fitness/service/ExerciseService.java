package com.epam.fitness.service;

import com.epam.fitness.dao.ExerciseDao;
import com.epam.fitness.entity.Exercise;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ExerciseService implements Service<Exercise> {
    private ExerciseDao exerciseDao;

    public ExerciseService(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    public List<Exercise> getAllByProgramId(int id) throws ServiceException {
        try {
            return exerciseDao.getAllByProgramId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Exercise> getAllByClientId(int id) throws ServiceException {
        try {
            return exerciseDao.getAllByClientId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Exercise> getAllByTrainerId(int id) throws ServiceException {
        try {
            return exerciseDao.getAllByTrainerId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeAllByProgramId(int programId) throws ServiceException {
        try {
            exerciseDao.removeAllByProgramId(programId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Exercise> getById(int id) throws ServiceException {
        try {
            return exerciseDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Exercise> getAll() throws ServiceException {
        try {
            return exerciseDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int save(Exercise exercise) throws ServiceException {
        try {
            return exerciseDao.save(exercise);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeById(int id) throws ServiceException {
        try {
            exerciseDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Exercise exercise) throws ServiceException {
        try {
            exerciseDao.update(exercise);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
