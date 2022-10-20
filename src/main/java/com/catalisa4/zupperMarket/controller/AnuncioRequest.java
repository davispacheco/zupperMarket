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

    @NotBlank(message = "Anúncio deve conter título.")
    private String nomeDoTitulo;

    @NotBlank(message = "Anúncio deve conter descrição.")
    private String descricao;

    private String urlFoto;

    private String descricaoFoto;

    @NotBlank(message = "Anúncio deve conter valor. Caso não tenha valor definido, coloque 0 (zero).")
    private double valor;

    @NotBlank(message = "Defina se o valor é negociável ou não.")
    private boolean seNegociavel;

    @NotBlank(message = "Informe a categoria do anúncio.")
    private Categorias categorias;

    @NotBlank(message = "Insira a quantidade disponível.")
    private int quantidade;

    @NotBlank(message = "Insira o estado onde o objeto ou serviço está localizado.")
    private String estado;

    @NotBlank(message = "Insira a cidade onde o objeto ou serviço está localizado.")
    private String cidade;

    @NotBlank(message = "Informe qual a forma de entrega do produto ou serviço.")
    private FormasDeEntrega entrega;

    private Status status;

    public AnuncioModel toAnuncioModel() {
        return new AnuncioModel(nomeDoTitulo, descricao, urlFoto, descricaoFoto,
                valor, seNegociavel, categorias, quantidade, estado, cidade,
                entrega, status, LocalDateTime.now());
    }

}
