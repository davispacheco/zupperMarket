package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Categorias;
import com.catalisa4.zupperMarket.enums.FormasDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnuncioResponse {

    private Long id;

    private String nomeDoTitulo;

    private String descricao;

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

    public AnuncioResponse(Long id, String nomeDoTitulo, String descricao, String urlFoto, double valor, boolean seNegociavel, Categorias categorias, int quantidade, String estado, String cidade, FormasDeEntrega entrega, Status status) {
        // verificar a necessidade desse construtor
    }

    public static AnuncioResponse fromAnunciosModel (AnuncioModel anuncioModel){
        return new AnuncioResponse(anuncioModel.getId(), anuncioModel.getNomeDoTitulo(),
                anuncioModel.getDescricao(), anuncioModel.getUrlFoto(), anuncioModel.getValor(),
                anuncioModel.isSeNegociavel(), anuncioModel.getCategorias(), anuncioModel.getQuantidade(),
                anuncioModel.getEstado(), anuncioModel.getCidade(), anuncioModel.getEntrega(),
                anuncioModel.getStatus());

                }

}
