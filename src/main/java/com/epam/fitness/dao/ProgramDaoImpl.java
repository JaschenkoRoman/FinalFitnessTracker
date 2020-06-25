package com.epam.fitness.dao;

import com.epam.fitness.builder.Builder;
import com.epam.fitness.entity.Program;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProgramDaoImpl extends AbstractDao<Program> implements ProgramDao {

    private static final String TABLE_NAME = "program";
    private static final String GET_BY_ID = "SELECT* FROM program WHERE id = ?";
    private static final String GET_ALL = "SELECT* FROM program ORDER BY creation_time DESC";
    private static final String GET_ALL_BY_CLIENT_ID = "SELECT* FROM program " +
            "WHERE client_id = ? ORDER BY creation_time DESC";
    private static final String GET_ALL_BY_TRAINER_ID = "SELECT* FROM program " +
            "WHERE trainer_id = ? ORDER BY creation_time DESC";
    private static final String REMOVE_BY_ID = "DELETE FROM program WHERE id = ?";
    private static final String UPDATE_NAME = "UPDATE program SET name = ? WHERE id = ?";
    private static final String UPDATE_CALORIES = "UPDATE program SET calories = ? WHERE id = ?";
    private static final String UPDATE_DURATION = "UPDATE program SET duration = ? WHERE id = ?";
    private static final String UPDATE_COST = "UPDATE program SET cost = ? WHERE id = ?";
    private static final String UPDATE_PROGRAM = "UPDATE program SET " +
            "name = ?, client_id = ?, trainer_id = ?, calories = ?," +
            " duration = ?, cost = ?, payment_time = ? WHERE id = ?";
    private static final String INSERT_PROGRAM = "INSERT INTO program " +
            "(name, client_id, trainer_id, calories, duration, cost) VALUES (?, ?, ?, ?, ?, ?)";

    public ProgramDaoImpl(Connection connection, Builder<Program> builder) {
        super(connection, builder);
    }

    @Override
    public List<Program> getAllByTrainerId(int trainerId) throws DaoException {
        return executeQuery(GET_ALL_BY_TRAINER_ID, trainerId);
    }

    @Override
    public List<Program> getAllByClientId(int clientId) throws DaoException {
        return executeQuery(GET_ALL_BY_CLIENT_ID, clientId);
    }

    @Override
    public void updateName(int id, String name) throws DaoException {
        executeUpdate(
                UPDATE_NAME,
                name,
                id);
    }

    @Override
    public void updateCalories(int id, int calories) throws DaoException {
        executeUpdate(
                UPDATE_CALORIES,
                calories,
                id);
    }

    @Override
    public void updateDuration(int id, int duration) throws DaoException {
        executeUpdate(
                UPDATE_DURATION,
                duration,
                id);
    }

    @Override
    public void updateCost(int id, BigDecimal cost) throws DaoException {
        executeUpdate(
                UPDATE_COST,
                cost,
                id);
    }

    @Override
    public Optional<Program> getById(int id) throws DaoException {
        return executeQueryForSingleResult(GET_BY_ID, id);
    }

    @Override
    public List<Program> getAll() throws DaoException {
        return executeQuery(GET_ALL);
    }

    @Override
    public int save(Program program) throws DaoException {
        User client = program.getClient();
        User trainer = program.getTrainer();
        executeUpdate(
                INSERT_PROGRAM,
                program.getName(),
                client.getId(),
                trainer.getId(),
                program.getCalories(),
                program.getDuration(),
                program.getCost());
        return getLastInsertId(TABLE_NAME);
    }

    @Override
    public void update(Program program) throws DaoException {
        User client = program.getClient();
        User trainer = program.getTrainer();
        executeUpdate(
                UPDATE_PROGRAM,
                program.getName(),
                client.getId(),
                trainer.getId(),
                program.getCalories(),
                program.getDuration(),
                program.getCost(),
                program.getId());
    }

    @Override
    public void removeById(int id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }


}
