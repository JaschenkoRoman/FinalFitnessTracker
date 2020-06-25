package com.epam.fitness.builder;

import com.epam.fitness.entity.Program;
import com.epam.fitness.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProgramBuilder implements Builder<Program> {
    @Override
    public Program build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int clientId = resultSet.getInt("client_id");
        User client = new User();
        client.setId(clientId);
        int trainerId = resultSet.getInt("trainer_id");
        User trainer = new User();
        trainer.setId(trainerId);
        int calories = resultSet.getInt("calories");
        int duration = resultSet.getInt("duration");
        BigDecimal cost = resultSet.getBigDecimal("cost");
        Timestamp creationTimestamp = resultSet.getTimestamp("creation_time");
        LocalDateTime creationTime = creationTimestamp.toLocalDateTime();
        Timestamp paymentTimestamp = resultSet.getTimestamp("payment_time");
        LocalDateTime paymentTime;
        if (paymentTimestamp != null) {
            paymentTime = paymentTimestamp.toLocalDateTime();
        } else {
            paymentTime = null;
        }
        return new Program(id, name, client, trainer, calories, duration, cost, creationTime, paymentTime);
    }
}
