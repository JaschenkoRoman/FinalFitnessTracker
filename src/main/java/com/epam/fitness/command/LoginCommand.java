package com.epam.fitness.command;

import com.epam.fitness.builder.Page;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private UserService UserService;

    public LoginCommand(UserService UserService) {
        this.UserService = UserService;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return Page.WELCOME_PAGE;
        }
        String login = request.getParameter("login");
        System.out.println(login);
        System.out.println(request.getCharacterEncoding());
        String password = request.getParameter("password");
        Optional<User> optionalUser = UserService.login(login, password);
        if (optionalUser.isPresent()) {
            session.setAttribute("user", optionalUser.get());
            page = Page.WELCOME_PAGE;
        } else {
            if (login != null && password != null) {
                request.setAttribute("error", "invalid login or password");
            }
            page = Page.LOGIN_PAGE /*+ "?error=invalid login or password"*/;
        }
        return page;
    }
}
