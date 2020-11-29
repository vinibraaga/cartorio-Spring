package com.docket.cartorio.entities;


import javax.persistence.*;

@Entity
@Table(name = "TB_CERTIDAO")
public class Certidao {

    @ManyToOne
    @JoinColumn(name = "fkCartorio")
    private Cartorio cartorio;

    @Id
    @GeneratedValue
    private Long idCertidao;

    private String nome;

    public Long getIdCertidao() {
        return idCertidao;
    }

    public void setIdCertidao(Long idCertidao) {
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
