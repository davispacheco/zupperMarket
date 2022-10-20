package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Categorias;
import com.catalisa4.zupperMarket.enums.FormasDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Getter
@Setter
public class AnuncioRequest {

    @NotBlank(message = "Anuncio deve conter titulo")
    String nomeDoTitulo;

    @NotBlank(message = "Anuncio deve conter descricao")
    String descricao;
    String urlFoto;
    String descricaoFoto;

    @NotBlank(message = "Anuncio deve conter valor, caso não tenha valor definido coloque 0 (zero) )")
    double valor;

    @NotBlank
    boolean seNegociavel;
    @NotBlank
    Categorias categorias;

    @NotBlank(message = "Insira a quantidade disponivel")
    int quantidade;

    @NotBlank(message = "Insira o estado onde o objeto ou serviço está localizado(a)")
    String estado;

    @NotBlank(message = "Insira a cidade onde o objeto ou serviço está localizado(a)")
    String cidade;

    @NotBlank
    FormasDeEntrega entrega;

    Status status;

    @NotBlank(message = "Apelido deve ser preenchido")
    String apelido;

    @NotBlank(message = "Você deve inserir seu email zup")
    String email;


    public AnuncioModel toAnuncioModel() {
        return new AnuncioModel(nomeDoTitulo, descricao, urlFoto, descricaoFoto,
                valor, seNegociavel, categorias, quantidade, estado, cidade,
                entrega, status, LocalDateTime.now());
    }

}
