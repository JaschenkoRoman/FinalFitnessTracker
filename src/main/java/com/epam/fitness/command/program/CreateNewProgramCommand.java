package com.epam.fitness.command.program;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Exercise;
import com.epam.fitness.entity.Program;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.service.ProgramService;
import com.epam.fitness.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class CreateNewProgramCommand implements Command {
    private final ProgramService programService;
    private final ExerciseService exerciseService;

    public CreateNewProgramCommand(ProgramService programService, ExerciseService exerciseService) {
        this.programService = programService;
        this.exerciseService = exerciseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int programId = createAndSaveProgram(request);
        for (int i = 1; isExerciseExistInForm(request, i); i++) {
            Exercise exercise = new Exercise();
            exercise.setProgramId(programId);
            fillExerciseWithInfoFromForm(request, exercise, i);
            exerciseService.save(exercise);
        }
        return Page.CREATE_PROGRAM_SUCCESS;

    }

    private boolean isExerciseExistInForm(HttpServletRequest request, int serialNumber) {
        return request.getParameterMap().containsKey("description[" + serialNumber +"]");
    }

    private int createAndSaveProgram(HttpServletRequest request) throws ServiceException {
        Program program = new Program();
        String name = request.getParameter("name");
        System.out.println(name);
        System.out.println(request.getCharacterEncoding());
        program.setName(name);
        String clientIdString = request.getParameter("clientId");
        int clientId = Integer.parseInt(clientIdString);
        User client = new User();
        client.setId(clientId);
        program.setClient(client);
        String caloriesString = request.getParameter("calories");
        int calories = Integer.parseInt(caloriesString);
        program.setCalories(calories);
        String durationString = request.getParameter("duration");
        int duration = Integer.parseInt(durationString);
        program.setDuration(duration);
        String costString = request.getParameter("cost");
        BigDecimal cost = new BigDecimal(costString);
        program.setCost(cost);
        HttpSession session = request.getSession();
        User trainer = (User)session.getAttribute("user");
        program.setTrainer(trainer);
        return programService.save(program);
    }

    private void fillExerciseWithInfoFromForm(HttpServletRequest request, Exercise exercise, int serialNumber) {
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
