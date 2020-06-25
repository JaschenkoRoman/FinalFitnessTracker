package com.epam.fitness.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int POOL_SIZE = 5;
    private static LinkedBlockingQueue<Connection> connections;
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool instance;
    private static ConnectionProvider connectionProvider = new ConnectionProvider();
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private ConnectionPool() {
        connections = new LinkedBlockingQueue<>(POOL_SIZE);
        try {
            Class.forName(JDBC_DRIVER);
            for (int i = 0; i < POOL_SIZE; i++) {
                connections.offer(connectionProvider.createConnection());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if(instance == null) {
            lock.lock();
            try {
                if(instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        connections.offer(connection);
    }


    public void closePool() {
        for (Connection connection: connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                /* logger.warn("Can not close the connection", e);*/
            }
        }

    }

}
