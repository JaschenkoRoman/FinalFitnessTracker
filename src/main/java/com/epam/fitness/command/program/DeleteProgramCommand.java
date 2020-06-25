package com.epam.fitness.command.program;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProgramCommand implements Command {
    private ProgramService programService;
    private ExerciseService exerciseService;

    public DeleteProgramCommand(ProgramService programService, ExerciseService exerciseService) {
        this.programService = programService;
        this.exerciseService = exerciseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String programIdString = request.getParameter("id");
        int programId = Integer.parseInt(programIdString);
        exerciseService.removeAllByProgramId(programId);
        programService.removeById(programId);
        return Page.DELETE_PROGRAM_SUCCESS;
    }
}
