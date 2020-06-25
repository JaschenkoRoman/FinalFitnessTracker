package com.epam.fitness.command.user;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowEditUserFormCommand implements Command {
    private UserService userService;

    public ShowEditUserFormCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        Optional<User> userOptional = userService.getById(id);
        userOptional.ifPresent(user -> request.setAttribute("user", user));
        Role[] roles = Role.class.getEnumConstants();
        request.setAttribute("roles", roles);
        return Page.EDIT_USER;
    }
}
