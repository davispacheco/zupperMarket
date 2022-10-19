package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.Enum.Categorias;
import com.catalisa4.zupperMarket.Enum.FormasDeEntrega;
import com.catalisa4.zupperMarket.Enum.Status;
import com.catalisa4.zupperMarket.model.AnunciosModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnuncioResponse {
    String nomeDoTitulo;
    String descricao;
    String urlFoto;
    String descricaoFoto;
    double valor;
    boolean seNegociavel;
    Categorias categorias;
    int quantidade;
    String estado;
    String cidade;
    FormasDeEntrega entrega;
    Status status;
    String apelido;
    String email;

    public AnuncioResponse(Long id, String nomeDoTitulo, String descricao, String urlFoto, double valor, boolean seNegociavel, Categorias categorias, int quantidade, String estado, String cudade, FormasDeEntrega entrega, Status status) {
        // verificar a necessidade desse construtor
    }

    public static AnuncioResponse fromAnunciosModel (AnunciosModel anunciosModel){
        return new AnuncioResponse(anunciosModel.getId(), anunciosModel.getNomeDoTitulo(),
                anunciosModel.getDescricao(), anunciosModel.getUrlFoto(), anunciosModel.getValor(),
                anunciosModel.isSeNegociavel(), anunciosModel.getCategorias(), anunciosModel.getQuantidade(),
                anunciosModel.getEstado(), anunciosModel.getCidade(), anunciosModel.getEntrega(),
                anunciosModel.getStatus());

                }


}
