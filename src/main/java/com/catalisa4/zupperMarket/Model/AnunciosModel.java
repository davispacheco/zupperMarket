package com.catalisa4.zupperMarket.Model;

import com.catalisa4.zupperMarket.enums.Categorias;
import com.catalisa4.zupperMarket.enums.FormasDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Validated
@Entity
@Table(name = "anuncio")

public class AnunciosModel {

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

    public String getNomeDoTitulo() {
        return nomeDoTitulo;
    }

    public void setNomeDoTitulo(String nomeDoTitulo) {
        this.nomeDoTitulo = nomeDoTitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getDescricaoFoto() {
        return descricaoFoto;
    }

    public void setDescricaoFoto(String descricaoFoto) {
        this.descricaoFoto = descricaoFoto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isSeNegociavel() {
        return seNegociavel;
    }

    public void setSeNegociavel(boolean seNegociavel) {
        this.seNegociavel = seNegociavel;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public FormasDeEntrega getEntrega() {
        return entrega;
    }

    public void setEntrega(FormasDeEntrega entrega) {
        this.entrega = entrega;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
