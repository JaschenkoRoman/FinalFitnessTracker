package com.epam.fitness.command;

import com.epam.fitness.builder.Page;
import com.epam.fitness.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("user").toString() + " is logout");
        session.invalidate();
        return Page.LOGIN_PAGE;
    }
}
