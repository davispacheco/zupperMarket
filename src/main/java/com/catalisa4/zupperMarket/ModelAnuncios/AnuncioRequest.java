package com.catalisa4.zupperMarket.ModelAnuncios;

import com.catalisa4.zupperMarket.Enuns.Categorias;
import com.catalisa4.zupperMarket.Enuns.FormasDeEntrega;
import com.catalisa4.zupperMarket.Enuns.Status;
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





}
