package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Categorias;
import com.catalisa4.zupperMarket.enums.FormasDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AnuncioResponse {

    private Long id;

    private String nomeDoTitulo;

    private String urlFoto;

    private String descricaoFoto;

    private double valor;

    private boolean seNegociavel;

    private Categorias categorias;

    private int quantidade;

    private String estado;

    private String cidade;

    private FormasDeEntrega entrega;

    private Status status;

    public static AnuncioResponse fromAnuncioModel(AnuncioModel anuncioModel) {
        return new AnuncioResponse(anuncioModel.getId(), anuncioModel.getNomeDoTitulo(), anuncioModel.getUrlFoto(),
                anuncioModel.getDescricaoFoto(), anuncioModel.getValor(), anuncioModel.isSeNegociavel(), anuncioModel.getCategorias(),
                anuncioModel.getQuantidade(), anuncioModel.getEstado(), anuncioModel.getCidade(), anuncioModel.getEntrega(),
                anuncioModel.getStatus());

    }

}
