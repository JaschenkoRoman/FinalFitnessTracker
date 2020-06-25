package com.epam.fitness.filter;

import com.epam.fitness.command.Command;
import com.epam.fitness.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnregisteredUserFilter implements  Filter {
    private static final String IGNORED_COMMANDS_DELIMITER = ",";
    private static final String IGNORED_COMMANDS = "ignoredCommands";

    private List<String> ignoredCommands = Collections.emptyList();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String commands = filterConfig.getInitParameter(IGNORED_COMMANDS);
        String[] splitCommands = commands.split(IGNORED_COMMANDS_DELIMITER);
        ignoredCommands = Arrays.asList(splitCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession(false);
//        String command = request.getParameter(Command.NAME);
//        boolean loggedIn = session != null && session.getAttribute("user") != null;
//        if(loggedIn || ignoredCommands.contains(command)) {
//            filterChain.doFilter(request, response);
//        } else {
//            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(Page.LOGIN_PAGE);
//            dispatcher.forward(request, response);
//        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String command = request.getParameter(Command.NAME);
        if (user == null && command != null && !ignoredCommands.contains(command)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(request.getContextPath());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
