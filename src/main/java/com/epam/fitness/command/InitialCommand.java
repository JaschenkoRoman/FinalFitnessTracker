package com.epam.fitness.command;

import com.epam.fitness.builder.Page;
import com.epam.fitness.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.fitness.builder.Page.LOGIN_PAGE;
import static com.epam.fitness.builder.Page.WELCOME_PAGE;

public class InitialCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String resultPage;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            resultPage = LOGIN_PAGE;
        } else {
            resultPage = WELCOME_PAGE;
        }
        return resultPage;
    }
}
