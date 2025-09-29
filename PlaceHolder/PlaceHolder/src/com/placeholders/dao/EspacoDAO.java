
package com.placeholders.dao;
import com.placeholders.database.ConnectionManager;
import com.placeholders.model.Espaco;

import java.math.BigDecima---------l;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspacoDAO {
    private ConnectionManager connectionManager;
    
    public EspacoDAO() {
        this.connectionManager = ConnectionManager.getInstance();
        
        
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public EspacoDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
      public boolean inserir(Espaco espaco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = connectionManager.getConnection();
            
            String sql = "INSERT INTO espacos (nome, descricao, capacidade, preco_hora, categoria_id, disponivel) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, espaco.getNome());
            stmt.setString(2, espaco.getDescricao());
            stmt.setInt(3, espaco.getCapacidade());
            stmt.setBigDecimal(4, espaco.getPrecoHora());
            stmt.setInt(5, espaco.getCategoriaId());
            stmt.setBooleanaco.isDisponivel());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    espaco.setId(rs.getInt(1));
                }
                rs.close();
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir espaço: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar statement: " + e.getMessage());
            }
            
            if (conn != null) {
                connectionManager.releaseConnection(conn);
            }
        }
    }
    
       public boolean atualizar(Espaco espaco) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = connectionManager.getConnection();
            
            String sql = "UPDATE espacos SET nome = ?, descricao = ?, capacidade = ?, " +
                         "preco_hora = ?, categoria_id = ?, disponivel = ? WHERE id = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, espaco.getNome());
            stmt.setString(2, espaco.getDescricao());
            stmt.setInt(3, espaco.getCapacidade());
            stmt.setBigDecimal(4, espaco.getPrecoHora());
            stmt.setInt(5, espaco.getCategoriaId());
            stmt.setBoolean(6, espaco.isDisponivel());
            stmt.setInt(7, espaco.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar espaço: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar statement: " + e.getMessage());
            }
            
            if (conn != null) {
                connectionManager.releaseConnection(conn);
            }
        }
    }
    
          public boolean excluir(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = connectionManager.getConnection();
            
            String sql = "DELETE FROMd = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir espaço: " + e.getMessage());
            return false finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar statement: " + e.getMessage());
            }
            
            if (conn != null) {
                connectionManager.releaseConnection(conn);
            }
        }
    }
        
         public Espaco buscarPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Espaco espaco = null;
        
        try {
            conn = connectionManager.getConnection();
            
            String sql = "SELECT * FROM espacos WHERE id = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                espaco = new Espaco();
                espaco.setId(rs.getInt("id"));
                espaco.setNome(rs.getString("nome"));
                espaco.setDescricao(rs.getString("descricao"));
                espaco.setCapacidade(rs.getInt("capacidade"));
                espaco.setPrecoHora(rs.getBigDecimal("preco_hora"));
                espaco.setCategoriaId(rs.getInt("categoria_id"));
                espaco.setDisponivel(rs.getBoolean("disponivel"));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar espaço por ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            
            if (conn != null) {
                connectionManager.releaseConnection(conn);
            }
        }
        
        return espaco;
    }
         
           public List<Espaco> listarTodos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Espaco> espacos = new ArrayList<>();
        
        try {
            conn = connectionManager.getConnection();
            
            String sql = "SELECT * FROM espacos";
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Espaco espaco = new Espaco();
                espaco.setId(rs.getInt("id"));
                espaco.setNome(rs.getString("nome"));
                espaco.setDescricao(rs.getString("descricao"));
                espaco.setCapacidade(rs.getInt("capacidade"));
                espaco.setPrecoHora(rs.getBigDecimal("preco_hora"));
                espaco.setCategoriaId(rs.getInt("categoria_id"));
                espaco.setDisponivel(rs.getBoolean("disponivel"));
                
                espacos.add(espaco);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar espaços: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            
            if (conn != null) {
                connectionManager.releaseConnection(conn);
            }
        }
        
        return espacos;
    }
           
            public List<Espaco> buscarPorCategoria(int categoriaId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Espaco> espacos = new ArrayList<>();
        
        try {
            conn = connectionManager.getConnection();
            
            String sql = "SELECT * FROM espacos WHERE categoria_id = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoriaId);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Espaco espaco = new Espaco();
                espaco.setId(rs.getInt("id"));
                espaco.setNome(rs.getString("nome"));
                espaco.setDescricao(rs.getString("descricao"));
                espaco.setCapacidade(rs.getInt("capacidade"));
                espaco.setPrecoHora(rs.getBigDecimal("preco_hora"));
                espaco.setCategoriaId(rs.getInt("categoria_id"));
                espaco.setDisponivel(rs.getBoolean("disponivel"));
                
                espacos.add(espaco);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar espaços por categoria: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            
            if (conn != null) {
                connectionManager.releaseConnection(conn);
            }
        }
        
        return espacos;
    }
    
    // Método para buscar espaços disponíveis
    public List<Espaco> buscarDisponiveis() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Espaco> espacos = new ArrayList<>();
        
        try {
            conn = connectionManager.getConnection();
            
            String sql = "SELECT * FROM espacos WHERE disponivel = TRUE";
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Espaco espaco = new Espaco();
                espaco.setId(rs.getInt("id"));
                espaco.setNome(rs.getString("nome"));
                espaco.setDescricao(rs.getString("descricao"));
                espaco.setCapacidade(rs.getInt("capacidade"));
                espaco.setPrecoHora(rs.getBigDecimal("preco_hora"));
                espaco.setCategoriaId(rs.getInt("categoria_id"));
                espaco.setDisponivel(rs.getBoolean("disponivel"));
                
                espacos.add(espaco);
            }
            
        } catch (SQLException
            System.err.println("Erro ao buscar espaços disponíveis: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
            
            if (conn != null) {
                connectionManager.releaseConnection(conn);
            }
        }
        
        return espacos;
    }
}
    

