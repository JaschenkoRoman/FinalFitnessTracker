package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String NAME = "command";
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
