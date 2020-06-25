package com.epam.fitness.factory;

import com.epam.fitness.dao.ExerciseDao;
import com.epam.fitness.dao.ProgramDao;
import com.epam.fitness.dao.UserDao;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.service.ProgramService;
import com.epam.fitness.service.UserService;

import java.sql.Connection;

public class ServiceFactory {
    private DaoFactory daoFactory;

    public ServiceFactory(Connection connection) {
        daoFactory = new DaoFactory(connection);
    }

    public UserService getUserService() {
        UserDao userDao = daoFactory.getUserDao();
        return new UserService(userDao);
    }
    public ProgramService getProgramService() {
        ProgramDao programDao = daoFactory.getProgramDao();
        ExerciseService exerciseService = getExerciseService();
        UserService userService = getUserService();
        return new ProgramService(programDao, exerciseService, userService);
    }
    public ExerciseService getExerciseService() {
        ExerciseDao exerciseDao = daoFactory.getExerciseDao();
        return new ExerciseService(exerciseDao);
    }

}
