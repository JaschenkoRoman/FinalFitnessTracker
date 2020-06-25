package com.epam.fitness.command.user;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteUserCommand implements Command {
    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        userService.removeById(id);
        List<User> trainers = userService.getUsersByRole(Role.TRAINER);
        List<User> users = userService.getUsersByRole(Role.CLIENT);
        users.addAll(trainers);
        request.setAttribute("users", users);
        return Page.ALL_USERS;
    }
}
