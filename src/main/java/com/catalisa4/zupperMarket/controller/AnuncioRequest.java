package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.Enum.Categorias;
import com.catalisa4.zupperMarket.Enum.FormasDeEntrega;
import com.catalisa4.zupperMarket.Enum.Status;
import com.catalisa4.zupperMarket.model.AnunciosModel;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class AnuncioRequest {

    @NotBlank(message = "Anuncio deve conter titulo")
    String nomeDoTitulo;

    @NotBlank(message = "Anuncio deve conter descricao")
    String descricao;
    String urlFoto;
    String descricaoFoto;

    @NotBlank (message = "Anuncio deve conter valor, caso não tenha valor definido coloque 0 (zero) )")
    double valor;

    boolean seNegociavel;
    Categorias categoria;

    @NotBlank (message = "Insira a quantidade disponivel")
    int quantidade;

    @NotBlank (message = "Insira o estado onde o objeto ou serviço está localizado(a)")
    String estado;

    @NotBlank (message = "Insira a cidade onde o objeto ou serviço está localizado(a)")
    String cidade;

    FormasDeEntrega entrega;
    Status status;

    @NotBlank (message = "Apelido deve ser preenchido")
    String apelido;

    @NotBlank (message = "Você deve inserir seu email zup")
    String email;


    public AnunciosModel toAnuncioModel(){
        return new AnunciosModel(null, nomeDoTitulo, descricao, urlFoto, descricaoFoto,
                valor, seNegociavel, categoria, quantidade, estado, cidade,
                entrega, status);
    }


}
