package com.epam.fitness.dao;

import com.epam.fitness.builder.Builder;
import com.epam.fitness.entity.Exercise;
import com.epam.fitness.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ExerciseDaoImpl extends AbstractDao<Exercise> implements ExerciseDao {

    private static final String TABLE_NAME = "exercise";
    private static final String GET_BY_ID = "SELECT* FROM exercise WHERE id = ?";
    private static final String GET_ALL = "SELECT* FROM exercise";
    private static final String GET_ALL_BY_PROGRAM_ID = "SELECT* FROM exercise WHERE program_id = ?";
    private static final String GET_ALL_BY_CLIENT_ID = "SELECT* FROM exercise e INNER JOIN " +
            "program pr ON e.program_id = pr.id WHERE pr.client_id = ?";
    private static final String GET_ALL_BY_TRAINER_ID = "SELECT* FROM exercise e INNER JOIN " +
            "program pr ON e.program_id = pr.id WHERE pr.trainer_id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM exercise WHERE id = ?";
    private static final String REMOVE_ALL_BY_PROGRAM_ID = "DELETE FROM exercise WHERE program_id = ?";
    private static final String UPDATE_EXERCISE = "UPDATE exercise SET " +
            "program_id = ?, description = ?, approaches = ?, repetitions = ?, day = ? WHERE id = ?";
    private static final String INSERT_EXERCISE = "INSERT INTO exercise " +
            "(program_id, description, approaches, repetitions, day) VALUES (?, ?, ?, ?, ?)";
    public ExerciseDaoImpl(Connection connection, Builder<Exercise> builder) {
        super(connection, builder);
    }

    @Override
    public Optional<Exercise> getById(int id) throws DaoException {
        return executeQueryForSingleResult(GET_BY_ID, id);
    }

    @Override
    public List<Exercise> getAll() throws DaoException {
        return executeQuery(GET_ALL);
    }

    @Override
    public int save(Exercise exercise) throws DaoException {
        executeUpdate(
                INSERT_EXERCISE,
                exercise.getProgramId(),
                exercise.getDescription(),
                exercise.getApproaches(),
                exercise.getRepetitions(),
                exercise.getDay());
        return getLastInsertId(TABLE_NAME);
    }

    @Override
    public void update(Exercise exercise) throws DaoException {
        executeUpdate(
                UPDATE_EXERCISE,
                exercise.getProgramId(),
                exercise.getDescription(),
                exercise.getApproaches(),
                exercise.getRepetitions(),
                exercise.getDay(),
                exercise.getId());
    }

    @Override
    public void removeById(int id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

    @Override
    public List<Exercise> getAllByProgramId(int programId) throws DaoException {
        return executeQuery(GET_ALL_BY_PROGRAM_ID, programId);
    }

    @Override
    public List<Exercise> getAllByClientId(int clientId) throws DaoException {
        return executeQuery(GET_ALL_BY_CLIENT_ID, clientId);
    }

    @Override
    public List<Exercise> getAllByTrainerId(int trainerId) throws DaoException {
        return executeQuery(GET_ALL_BY_TRAINER_ID, trainerId);
    }

    @Override
    public void removeAllByProgramId(int programId) throws DaoException {
        executeUpdate(REMOVE_ALL_BY_PROGRAM_ID, programId);
    }
}
