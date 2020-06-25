package com.epam.fitness.command.program;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Exercise;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class UpdateProgramCommand implements Command {
    private final ProgramService programService;
    private final ExerciseService exerciseService;

    public UpdateProgramCommand(ProgramService programService, ExerciseService exerciseService) {
        this.programService = programService;
        this.exerciseService = exerciseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("id");
        int programId = Integer.parseInt(idString);
        updateProgramCommonInfo(request, programId);
        List<Exercise> exercisesFromDB = exerciseService.getAllByProgramId(programId);
        for (int i = 1; isExerciseExistInForm(request, i); i++) {
            Exercise exercise = new Exercise();
            exercise.setProgramId(programId);
            fillExerciseWithInfoFromForm(request, exercise, i);
            for (Exercise exerciseFromDB: exercisesFromDB) {
                if(exercise.getId() == exerciseFromDB.getId()) {
                    exercisesFromDB.remove(exerciseFromDB);
                    break;
                }
            }
            if(exercise.getId() != 0) {
                exerciseService.update(exercise);
            } else {
                exerciseService.save(exercise);
            }
        }
        for (Exercise exercise: exercisesFromDB) {
            exerciseService.removeById(exercise.getId());
        }
        return Page.UPDATE_PROGRAM_SUCCESS;

    }

    private boolean isExerciseExistInForm(HttpServletRequest request, int serialNumber) {
        return request.getParameterMap().containsKey("exerciseId[" + serialNumber +"]");
    }

    private void updateProgramCommonInfo(HttpServletRequest request, int id) throws ServiceException {
        String caloriesString = request.getParameter("calories");
        if (caloriesString != null) {
            int calories = Integer.parseInt(caloriesString);
            programService.updateCalories(id, calories);
        }
        String durationString = request.getParameter("duration");
        if (durationString != null) {
            int duration = Integer.parseInt(durationString);
           programService.updateDuration(id, duration);
        }
        String costString = request.getParameter("cost");
        if (costString != null) {
            BigDecimal cost = new BigDecimal(costString);
            programService.updateCost(id, cost);
        }
    }

    /*may be in utils.exerciseFormBuilder.class create public static method*/
    private void fillExerciseWithInfoFromForm(HttpServletRequest request, Exercise exercise, int serialNumber) {
        String exerciseIdString = request.getParameter("exerciseId[" + serialNumber + "]");
        int exerciseId = Integer.parseInt(exerciseIdString);
        exercise.setId(exerciseId);
        String description = request.getParameter("description[" + serialNumber + "]");
        exercise.setDescription(description);
        String approachesString = request.getParameter("approaches[" + serialNumber + "]");
        int approaches = Integer.parseInt(approachesString);
        exercise.setApproaches(approaches);
        String repetitionsString = request.getParameter("repetitions[" + serialNumber + "]");
        int repetitions = Integer.parseInt(repetitionsString);
        exercise.setRepetitions(repetitions);
        String dayString = request.getParameter("day[" + serialNumber + "]");
        int day = Integer.parseInt(dayString);
        exercise.setDay(day);
    }

}
