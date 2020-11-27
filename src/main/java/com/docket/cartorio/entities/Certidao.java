package com.docket.cartorio.entities;


import javax.persistence.*;

@Entity
public class Certidao {

    @Id
    @GeneratedValue
    private Integer idCertidao;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "fkCartorio")
    private Cartorio cartorio;

    public Integer getIdCertidao() {
        return idCertidao;
    }

    public void setIdCertidao(Integer idCertidao) {
        this.idCertidao = idCertidao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cartorio getCartorio() {
        return cartorio;
    }

    public void setCartorio(Cartorio cartorio) {
        this.cartorio = cartorio;
    }

}
