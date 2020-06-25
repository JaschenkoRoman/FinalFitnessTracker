package com.epam.fitness.dao;

import com.epam.fitness.entity.Exercise;
import com.epam.fitness.exception.DaoException;

import java.util.List;

public interface ExerciseDao extends Dao<Exercise> {
    List<Exercise> getAllByProgramId(int id) throws DaoException;
    List<Exercise> getAllByClientId(int id) throws DaoException;
    List<Exercise> getAllByTrainerId(int id) throws DaoException;
    void removeAllByProgramId(int programId) throws DaoException;
}
