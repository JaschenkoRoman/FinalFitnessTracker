package com.epam.fitness.command.user;

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
import java.util.Optional;

public class ShowEditProgramFormCommand implements Command {
    private final ProgramService programService;

    public ShowEditProgramFormCommand(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = Page.WELCOME_PAGE;
        String programIdString = request.getParameter("id");
        int programId = Integer.parseInt(programIdString);
        Optional<Program> programOptional = programService.getByIdWithExercises(programId);
        if(programOptional.isPresent()) {
            Program program = programOptional.get();
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            int userId = user.getId();
            int clientId = program.getClient().getId();
            int trainerId = program.getTrainer().getId();
            if(userId == clientId || userId == trainerId || user.getRole() == Role.ADMIN) {
                request.setAttribute("program", program);
                page = Page.EDIT_PROGRAM;
            }
        }
        return page;
    }
}
