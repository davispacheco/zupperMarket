package com.catalisa4.zupperMarket.model;

import com.catalisa4.zupperMarket.enums.Categorias;
import com.catalisa4.zupperMarket.enums.FormasDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "anuncios")

public class AnuncioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String  nomeDoTitulo;

    @Column(length = 300, nullable = false)
    private String descricao;

    @Column(length = 60, nullable = false)
    private String urlFoto;

    @Column(length = 300, nullable = false)
    private String descricaoFoto;

    @Column(length = 12, nullable = false)
    private double valor;

    @Column(length = 5, nullable = false)
    private boolean seNegociavel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categorias categorias;

    @Column(length = 3, nullable = false)
    private int quantidade;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(length = 30, nullable = false)
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormasDeEntrega entrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private LocalDateTime dataHoraCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioModel usuario;

    public AnuncioModel(String nomeDoTitulo, String descricao, String urlFoto, String descricaoFoto, double valor,
                        boolean seNegociavel, Categorias categorias, int quantidade, String estado, String cidade,
                        FormasDeEntrega entrega, Status status, LocalDateTime dataHoraCriacao) {
        this.nomeDoTitulo = nomeDoTitulo;
        this.descricao = descricao;
        this.urlFoto = urlFoto;
        this.descricaoFoto = descricaoFoto;
        this.valor = valor;
        this.seNegociavel = seNegociavel;
        this.categorias = categorias;
        this.quantidade = quantidade;
        this.estado = estado;
        this.cidade = cidade;
        this.entrega = entrega;
        this.status = status;
        this.dataHoraCriacao = dataHoraCriacao;
    }
}
