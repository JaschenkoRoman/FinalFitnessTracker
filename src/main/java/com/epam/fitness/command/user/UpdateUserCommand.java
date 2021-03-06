package com.epam.fitness.command.user;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Optional;

public class UpdateUserCommand implements Command {
    private UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String resultPage = Page.EDIT_USER;
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surName = request.getParameter("sur_name");
        String birthDateString = request.getParameter("birth_date");
        LocalDate birthDate = LocalDate.parse(birthDateString);
        String roleString = request.getParameter("role");
        Role role = Role.valueOf(roleString);
        Optional<User> userOptional = userService.getByLogin(login);
        User user = new User(id, login, password, name, surName, birthDate, role);
        if (!userOptional.isPresent() || userOptional.get().getId() == id) {
            userService.update(user);
            resultPage = Page.UPDATE_USER_SUCCESS;
        } else {
            request.setAttribute("error", "login already belongs to another user");
            request.setAttribute("user", user);
            Role[] roles = Role.class.getEnumConstants();
            request.setAttribute("roles", roles);
        }
        return resultPage;
    }
}
