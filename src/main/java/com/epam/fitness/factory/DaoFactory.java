package com.epam.fitness.factory;

import com.epam.fitness.builder.ExerciseBuilder;
import com.epam.fitness.builder.ProgramBuilder;
import com.epam.fitness.builder.UserBuilder;
import com.epam.fitness.dao.*;

import java.sql.Connection;

public class DaoFactory {

    private final Connection connection;

    public DaoFactory(Connection connection) {
        this.connection = connection;
    }

    public UserDao getUserDao() {
        return new UserDaoImpl(connection, new UserBuilder());
    }

    public ProgramDao getProgramDao() {
        return new ProgramDaoImpl(connection, new ProgramBuilder());
    }

    public ExerciseDao getExerciseDao() {
        return new ExerciseDaoImpl(connection, new ExerciseBuilder());
    }

}
