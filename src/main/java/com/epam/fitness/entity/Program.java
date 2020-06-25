package com.epam.fitness.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private int id;
    private String name;
    private User client;
    private User trainer;
    private int calories;
    private int duration;
    private BigDecimal cost;
    private LocalDateTime creationTime;
    private LocalDateTime paymentTime;
    private List<Exercise> exercises = new ArrayList<>();

    public Program(int id, String name, User client, User trainer, int calories,
                   int duration, BigDecimal cost, LocalDateTime creationTime,
                   LocalDateTime paymentTime, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.client = client;
        this.trainer = trainer;
        this.calories = calories;
        this.duration = duration;
        this.cost = cost;
        this.creationTime = creationTime;
        this.paymentTime = paymentTime;
        this.exercises = exercises;
    }

    public Program(int id, String name, User client, User trainer, int calories, int duration,
                   BigDecimal cost, LocalDateTime creationTime, LocalDateTime paymentTime) {
        this.id = id;
        this.name = name;
        this.client = client;
        this.trainer = trainer;
        this.calories = calories;
        this.duration = duration;
        this.cost = cost;
        this.creationTime = creationTime;
        this.paymentTime = paymentTime;
    }

    public Program(String name, User client, User trainer, int calories, int duration, BigDecimal cost) {
        this.name = name;
        this.client = client;
        this.trainer = trainer;
        this.calories = calories;
        this.duration = duration;
        this.cost = cost;
    }

    public Program() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client=" + client +
                ", trainer=" + trainer +
                ", calories=" + calories +
                ", duration=" + duration +
                ", cost=" + cost +
                ", creationTime=" + creationTime +
                ", paymentTime=" + paymentTime +
                ", exercises=" + exercises +
                '}';
    }
}
