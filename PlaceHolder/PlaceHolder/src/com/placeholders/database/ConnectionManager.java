
package com.placeholders.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;



public class ConnectionManager {
    
          private static ConnectionManager instance;
    
    
    private static final String URL = "jdbc:mysql://localhost:3306/PlaceHolder";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final int MAX_POOL_SIZE = 10;
    
    
    private BlockingQueue<Connection> connectionPool;
    private int createdConnections = 0;
    
  
     
    private ConnectionManager() {
        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            
          
            connectionPool = new ArrayBlockingQueue<>(MAX_POOL_SIZE);
            
          
            for (int i = 0; i < 5; i++) {
                connectionPool.offer(createNewConnection());
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            throw new RuntimeException("Driver JDBC não encontrado", e);
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o pool de conexões: " + e.getMessage());
            throw new RuntimeException("Erro ao inicializar o pool de conexões", e);
        }
    }
    

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    
    
    private Connection createNewConnection() throws SQLException {
        Properties props = new Properties();
props.setProperty("user", USER);
props.setProperty("password", PASSWORD);    
props.setProperty("useSSL", "false");
props.setProperty("serverTimezone", "UTC");
props.setProperty("allowPublicKeyRetrieval", "true");
        
        Connection conn = DriverManager.getConnection(URL, props);
        createdConnections++;
        return conn;
    }
    
    /**
     * Obtém uma conexão do pool
     */
    public Connection getConnection() throws SQLException {
        try {
            // Tenta obter uma conexão do pool
            Connection conn = connectionPool.poll();
            
            // Se não há conexões disponíveis e ainda podemos criar mais
            if (conn == null && createdConnections < MAX_POOL_SIZE) {
                return createNewConnection();
            } 
            // Se não há conexões dispon
            else if (conn == null) {
                // Espera até que uma conexão seja liberada
                try {
                    conn = connectionPool.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new SQLException("Interrompido enquanto aguardava por uma conexão");
                }
            }
            
            // Verifica se a conexão ainda é válida
            if (conn.isClosed() || !conn.isValid(1)) {
                conn = createNewConnection();
            }
            
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro ao obter conexão: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Devolve uma conexão ao pool
     */
    public void releaseConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed() && conn.isValid(1)) {
                    connectionPool.offer(conn);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao liberar conexão: " + e.getMessage());
                try {
                    conn.close();
                    createdConnections--;
                } catch (SQLException ex) {
                    System.err.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Fecha todas as conexões no pool
     */
    public void closeAllConnections() {
        for (Connection conn : connectionPool) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        connectionPool.clear();
        createdConnections = 0;
    }
}