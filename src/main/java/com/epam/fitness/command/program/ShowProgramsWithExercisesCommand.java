package com.epam.fitness.command.program;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Program;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowProgramsWithExercisesCommand implements Command {
    private final ProgramService programService;

    public ShowProgramsWithExercisesCommand(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Program> programs;
        int id = user.getId();
        Role role = user.getRole();
        switch (role) {
            case TRAINER:
                programs = programService.getAllProgramsWithExercisesByTrainerId(id);
                break;
            case CLIENT:
                programs = programService.getAllProgramsWithExercisesByClientId(id);
                break;
            default:
                programs = programService.getAllProgramsWithExercisesByClientId(id);/*??????*/
        }
        request.setAttribute("programs", programs);
        return Page.ALL_PROGRAMS;
    }
}
