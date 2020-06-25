package com.epam.fitness.command;

import com.epam.fitness.builder.Page;
import com.epam.fitness.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static final String LANGUAGE_ATTRIBUTE = "language";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = Page.WELCOME_PAGE;
        String language = request.getParameter(LANGUAGE_ATTRIBUTE);
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE, language.toLowerCase());
        if (session.getAttribute("user") == null) {
            page = Page.LOGIN_PAGE;
        }
        return page;
    }
}
