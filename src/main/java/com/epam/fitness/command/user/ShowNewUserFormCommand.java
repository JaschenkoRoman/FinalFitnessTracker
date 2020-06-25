package com.epam.fitness.command.user;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.entity.Role;
import com.epam.fitness.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowNewUserFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Role[] roles = Role.class.getEnumConstants();
        request.setAttribute("roles", roles);
        return Page.NEW_USER;
    }
}
