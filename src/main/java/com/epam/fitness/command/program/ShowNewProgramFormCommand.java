package com.epam.fitness.command.program;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowNewProgramFormCommand implements Command {
    private final UserService userService;

    public ShowNewProgramFormCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> clients = userService.getUsersByRole(Role.CLIENT);
        request.setAttribute("clients", clients);
        return Page.NEW_PROGRAM;
    }
}
