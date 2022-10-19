package com.catalisa4.zupperMarket.ModelAnuncios;


import com.catalisa4.zupperMarket.enums.Categorias;
import com.catalisa4.zupperMarket.enums.FormasDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Validated
@Data
@Entity
@Table(name = "anuncio")

public class AnunciosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String  nomeDoTitulo;

    @Column(length = 300, nullable = false)
    private String descricao;

    @Column(length = 30, nullable = false)
    private String urlFoto;

    @Column(length = 15, nullable = false)
    private String descricaoFoto;

    @Column(length = 12, nullable = false)
    private double valor;

    @Column(length = 15, nullable = false)
    private boolean seNegociavel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categorias categorias;

    @Column(length = 3, nullable = false)
    private int quantidade ;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(length = 30, nullable = false)
    private String cudade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormasDeEntrega entrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;



}
