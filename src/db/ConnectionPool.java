package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
    private static ConnectionPool instance;
    private ArrayList<Connection> connections;
    private static final int MAX_CONNECTIONS = 5;

    // private ctor to avoid creating new instances (new objects)
    private ConnectionPool() {
        connections = new ArrayList<>();
        // create 5 connections
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                connections.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/coupons?serverTimezone=UTC", "root", "1234"));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // return the ONLY instance of ConnectionPool
    public static ConnectionPool getInstance() {
        //Double-checked locking
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null)
                    instance = new ConnectionPool();
            }
        }
        return instance;
    }


    // get "free" connection
    public synchronized Connection getConnection() {
        while (connections.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Connection con = connections.get(0);
        connections.remove(0);
        return con;
    }

    // return "used" connection back to the connections ArrayList
    public synchronized void restoreConnection(Connection con) {
        connections.add(con);
        notify();
    }

    // close all connections when shutting down the system
    public synchronized void closeAllConnections() {
        while (connections.size() < MAX_CONNECTIONS) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        for (Connection con : connections) {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }
}
