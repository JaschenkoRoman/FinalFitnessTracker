package com.epam.fitness.service;

import com.epam.fitness.dao.ProgramDao;
import com.epam.fitness.entity.Exercise;
import com.epam.fitness.entity.Program;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProgramService implements Service<Program> {
    private ProgramDao programDao; //may be final also everywhere
    private ExerciseService exerciseService;
    private UserService userService;

    public ProgramService(ProgramDao programDao, ExerciseService exerciseService, UserService userService) {
        this.programDao = programDao;
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

    public void updateName(int id, String name) throws ServiceException {
        try {
            programDao.updateName(id, name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateCalories(int id, int calories) throws ServiceException {
        try {
            programDao.updateCalories(id, calories);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateDuration(int id, int duration) throws ServiceException {
        try {
            programDao.updateDuration(id, duration);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateCost(int id, BigDecimal cost) throws ServiceException {
        try {
            programDao.updateCost(id, cost);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Program> getAllProgramsWithExercisesByClientId(int id) throws ServiceException {
        try {
            List<Program> programs = programDao.getAllByClientId(id);
            List<Exercise> exercises = exerciseService.getAllByClientId(id);
            for (Program program: programs) {
                addClientAndTrainerToProgram(program);
            }
            fillProgramListWithExercises(programs, exercises);
            sortExercisesByDay(programs);
            return programs;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Program> getAllProgramsByClientId(int id) throws ServiceException {
        try {
            List<Program> programs = programDao.getAllByClientId(id);
            for (Program program: programs) {
                addClientAndTrainerToProgram(program);
            }
            return programs;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Program> getAllProgramsWithExercisesByTrainerId(int id) throws ServiceException {
        try {
            List<Program> programs = programDao.getAllByTrainerId(id);
            List<Exercise> exercises = exerciseService.getAllByTrainerId(id);
            for (Program program: programs) {
                addClientAndTrainerToProgram(program);
            }
            fillProgramListWithExercises(programs, exercises);
            sortExercisesByDay(programs);
            return programs;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Program> getAllProgramsByTrainerId(int id) throws ServiceException {
        try {
            List<Program> programs = programDao.getAllByTrainerId(id);
            for (Program program: programs) {
                addClientAndTrainerToProgram(program);
            }
            return programs;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Program> getByIdWithExercises(int id) throws ServiceException {
        try {
            Optional<Program> programOptional = programDao.getById(id);
            List<Exercise> exercises = exerciseService.getAllByProgramId(id);
            programOptional.ifPresent(program -> program.setExercises(exercises));
            return programOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Program> getById(int id) throws ServiceException {
        try {
            return programDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Program> getAll() throws ServiceException {
        try {
            List<Program> programs = programDao.getAll();
            for (Program program: programs) {
                addClientAndTrainerToProgram(program);
            }
            return programs;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int save(Program program) throws ServiceException {
        try {
            return programDao.save(program);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeById(int id) throws ServiceException {
        try {
            programDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Program program) throws ServiceException {
        try {
            programDao.update(program);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void addClientAndTrainerToProgram(Program program) throws ServiceException{
        int clientId = program.getClient().getId();
        int trainerId = program.getTrainer().getId();
        Optional<User> client = userService.getById(clientId);
        Optional<User> trainer = userService.getById(trainerId);
        client.ifPresent(program::setClient);
        trainer.ifPresent(program::setTrainer);
    }

    private void fillProgramListWithExercises(List<Program> programs, List<Exercise> exercises) {
        for(Program program: programs) {
            for (Exercise exercise: exercises) {
                if(exercise.getProgramId() == program.getId()) {
                    program.addExercise(exercise);
                }
            }
        }
    }

    private void sortExercisesByDay(List<Program> programs) {
        for (Program program: programs) {
            List<Exercise> exercises = program.getExercises();
            exercises.sort(Comparator.comparingInt(Exercise::getDay));
        }
    }
}
