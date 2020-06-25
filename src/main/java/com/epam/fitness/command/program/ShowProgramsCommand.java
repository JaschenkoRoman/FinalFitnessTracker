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

public class ShowProgramsCommand implements Command {
    private final ProgramService programService;

    public ShowProgramsCommand(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int id = user.getId();
        Role role = user.getRole();
        List<Program> programs;
        switch (role) {
            case TRAINER:
                programs = programService.getAllProgramsByTrainerId(id);
                break;
            case CLIENT:
                programs = programService.getAllProgramsByClientId(id);
                break;
            case ADMIN:
                programs = programService.getAll();
                break;
            default:
                programs = programService.getAllProgramsByClientId(id);/*??????*/
        }
        request.setAttribute("programs", programs);
        return Page.ALL_PROGRAM_PAYMENTS;
    }
}
