package com.epam.fitness;

import com.epam.fitness.builder.Page;
import com.epam.fitness.command.Command;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.factory.CommandFactory;
import com.epam.fitness.factory.ServiceFactory;
import com.epam.fitness.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class FitnessController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance();
        System.out.println("ConnectionPool is created");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter(Command.NAME);
        Connection connection = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connection = connectionPool.getConnection();
            ServiceFactory serviceFactory = new ServiceFactory(connection);
            CommandFactory commandFactory = new CommandFactory(serviceFactory);
            Command action = commandFactory.create(command);
            String page = action.execute(req, resp);
            dispatch(req, resp, page);
        } catch (ServiceException e) {
            dispatch(req, resp, Page.ERROR_PAGE);
//            resp.sendRedirect(Page.ERROR_PAGE);
//            resp.sendError(404);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }

        /*try {
            RequestHelper helper = new RequestHelper(req);
            Command command =  helper.getCommand();

            // delegate request to a command object helper
            resultPage = command.execute(req, resp);
        }
        catch (Exception e) {
            System.out.println("EmployeeController" + e.getMessage());
            resultPage = ApplicationResources.getInstance().getErrorPage(e);
        }

        dispatch(req, resp, resultPage);*/
      /*  String name = req.getParameter("name");
        String NAME = "Roman";
        String password = req.getParameter("password");
        String PASS = "123";
        if(name != null & NAME.equals(name) & password != null & PASS.equals(password)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("rrr/fitness.jsp");
            dispatcher.forward(req, resp);
        }*/
       /* String command = req.getParameter("command");
        Command action = CommandFactory.create(command);
        String page;
        try {
            page = action.execute(req, resp);
        } catch (ServiceException e) {
            req.setAttribute("errorMessage", e.getMessage());
            page = "error.jsp";
        }
        dispatch(req, resp, page);*/

        /*String login = req.getParameter("login");
        String password = req.getParameter("password");
        User User = new User();
        if (login != null && password != null) {
            try(Connection connection = ConnectionPool.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(SQL_LOGIN_PASS);
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    User.setId(id);
                    String log = resultSet.getString("login");
                    User.setLogin(log);
                    String pass = resultSet.getString("password");
                    User.setPassword(pass);
                    String role = resultSet.getString("role");
                    User.setRole(Role.valueOf(role));
                    HttpSession session = req.getSession();
                    session.setAttribute("User", User);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
                    dispatcher.forward(req, resp);
                }
                ConnectionPool.getInstance().releaseConnection(connection);
                ConnectionPool.getInstance().closePool();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(User.getLogin()==null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("rrr/fitness.jsp");
            dispatcher.forward(req, resp);
        }*/

    }
    protected void dispatch(HttpServletRequest request,
                            HttpServletResponse response,
                            String page)
            throws  javax.servlet.ServletException,
            IOException {
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
        //add request.sendRedirect();
    }

    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}
