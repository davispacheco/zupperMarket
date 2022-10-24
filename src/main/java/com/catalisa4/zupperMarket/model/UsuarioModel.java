package com.catalisa4.zupperMarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String nomeCompleto;

    @Column(length = 60, nullable = false)
    private String apelido;

    @Column(length = 60, nullable = false, unique = true)
    private String email;

    @Column(length = 60)
    private String celular;

    @Column(length = 60, nullable = false)
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<AnuncioModel> anuncios = new ArrayList<>();

    public UsuarioModel(String nomeCompleto, String apelido, String email, String celular, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
    }


}
