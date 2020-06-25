package com.epam.fitness.builder;

import com.epam.fitness.entity.Program;
import com.epam.fitness.entity.Role;
import com.epam.fitness.entity.User;
import com.epam.fitness.pool.ConnectionPool;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
        String roleName = resultSet.getString("role");
        Role role = Role.valueOf(roleName);
        return new User(id, login, password, name, surname, birthDate, role);
    }

/*    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/fitness";
        String name = "root";
        String pass = "102030";
        try*//*(Connection connection = ConnectionPool.getInstance().getConnection())*//* {
   *//*         System.out.println("connection1 is successful");*//*
            Connection connection2 = DriverManager.getConnection(url, name, pass);
            System.out.println("connection2 is successful");
            Statement statement = connection2.createStatement();
            ResultSet resultSet = statement.executeQuery("select* from program as pr where " +
                    " creation_time = (select max(creation_time) from program as pr2 " +
                    "where pr.id = pr2.id) ORDER BY pr.id");
            for(int i = 0; i<10; i++) {
                ProgramBuilder programBuilder = new ProgramBuilder();
                Program program = programBuilder.build(resultSet);
                System.out.println(program.toString());
            }
            connection2.close();
*//*            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO fitness.User (login, password, role) VALUES ('javista', '12345', 'admin');");*//*
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
