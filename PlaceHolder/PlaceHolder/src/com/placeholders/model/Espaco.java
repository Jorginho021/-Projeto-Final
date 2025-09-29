
package com.placeholders.model;

import java.math.BigDecimal;

public class Espaco {
    private int id;
    
    private String descricao;
    private int capacidade;
    private BigDecimal precoHora;
    private int categoriaId;
    private boolean disponivel;
    private String nome;
    
     public Espaco() {}
    
    public Espaco(String nome, String descricao, int capacidade, BigDecimal precoHora, int categoriaId, boolean disponivel) {
        this.nome = nome;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.precoHora = precoHora;
        this.categoriaId = categoriaId;
        this.disponivel = disponivel;
    }
    
      public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    public BigDecimal getPrecoHora() {
        return precoHora;
    }
    
    public void setPrecoHora(BigDecimal precoHora) {
        this.precoHora = precoHora;
    }
    
    public int getCategoriaId() {
        return categoriaId;
    }
    
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
     
    public boolean isDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
     @Override
    public String toString() {
        return "Espaco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacidade=" + capacidade +
                ", precoHora=" + precoHora +
                ", disponivel=" + disponivel +
                '}';
    }

    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    

