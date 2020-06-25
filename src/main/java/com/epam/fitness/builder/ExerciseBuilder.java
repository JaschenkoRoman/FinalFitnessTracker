package com.epam.fitness.builder;

import com.epam.fitness.entity.Exercise;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseBuilder implements Builder<Exercise> {
    @Override
    public Exercise build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int programId = resultSet.getInt("program_id");
        String description = resultSet.getString("description");
        int approaches = resultSet.getInt("approaches");
        int repetitions = resultSet.getInt("repetitions");
        int day = resultSet.getInt("day");
        return new Exercise(id, programId, description, approaches, repetitions, day);
    }
}
