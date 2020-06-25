package com.epam.fitness.entity;

import java.util.Objects;

public class Exercise {
    private int id;
    private int programId;
    private String description;
    private int approaches;
    private int repetitions;
    private int day;

    public Exercise(int id, int programId, String description, int approaches, int repetitions, int day) {
        this.id = id;
        this.programId = programId;
        this.description = description;
        this.approaches = approaches;
        this.repetitions = repetitions;
        this.day = day;
    }

    public Exercise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getApproaches() {
        return approaches;
    }

    public void setApproaches(int approaches) {
        this.approaches = approaches;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id == exercise.id &&
                programId == exercise.programId &&
                approaches == exercise.approaches &&
                repetitions == exercise.repetitions &&
                day == exercise.day &&
                Objects.equals(description, exercise.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, programId, description, approaches, repetitions, day);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", programId=" + programId +
                ", description='" + description + '\'' +
                ", approaches=" + approaches +
                ", repetitions=" + repetitions +
                ", day=" + day +
                '}';
    }
}
