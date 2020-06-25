package com.epam.fitness.dao;

import com.epam.fitness.entity.Program;
import com.epam.fitness.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProgramDao extends Dao<Program> {
    List<Program> getAllByTrainerId(int trainerId) throws DaoException;
    List<Program> getAllByClientId(int clientId) throws DaoException;
    void updateName(int id, String name) throws DaoException;
    void updateCalories(int id, int calories) throws DaoException;
    void updateDuration(int id, int duration) throws DaoException;
    void updateCost(int id, BigDecimal cost) throws DaoException;
}
