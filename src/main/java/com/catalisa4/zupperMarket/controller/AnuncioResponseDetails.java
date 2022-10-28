package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AnuncioResponseDetails {

    private Long id;

    private String nomeDoTitulo;

    private String descricao;

    private String urlFoto;

    private String descricaoFoto;

    private double valor;

    private boolean seNegociavel;

    private Categoria categoria;

    private int quantidade;

    private String estado;

    private String cidade;

    private FormaDeEntrega entrega;

    private Status status;

    public static AnuncioResponseDetails fromAnuncioModel(AnuncioModel anuncioModel) {
        return new AnuncioResponseDetails(anuncioModel.getId(), anuncioModel.getNomeDoTitulo(), anuncioModel.getDescricao(), anuncioModel.getUrlFoto(),
                anuncioModel.getDescricaoFoto(), anuncioModel.getValor(), anuncioModel.isSeNegociavel(), anuncioModel.getCategoria(),
                anuncioModel.getQuantidade(), anuncioModel.getEstado(), anuncioModel.getCidade(), anuncioModel.getEntrega(),
                anuncioModel.getStatus());

    }

    public static List<AnuncioResponseDetails> fromAnuncioModelList(List<AnuncioModel> anuncios) {
        List<AnuncioResponseDetails> novaLista = new ArrayList<>();
        for (AnuncioModel anuncio : anuncios) {
            AnuncioResponseDetails novoAnuncio = AnuncioResponseDetails.fromAnuncioModel(anuncio);
            novaLista.add(novoAnuncio);
        }
        return novaLista;
    }
}
