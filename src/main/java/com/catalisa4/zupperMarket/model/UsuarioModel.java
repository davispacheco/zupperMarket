package com.catalisa4.zupperMarket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

    public UsuarioModel(String nomeCompleto, String apelido, String email) {
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.email = email;
    }
}
