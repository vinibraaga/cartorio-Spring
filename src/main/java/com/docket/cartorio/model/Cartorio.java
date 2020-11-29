package com.docket.cartorio.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_CARTORIO")
public class Cartorio implements Serializable {

    private static final long serialVersionUID = 5050;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String endereco;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }
}
