package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.FormaDeEntrega;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AnuncioResponse {

    private Long id;

    private String nomeDoTitulo;

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

    private UsuarioResponseDetails usuario;

    public static AnuncioResponse fromAnuncioModel(AnuncioModel anuncioModel, UsuarioModel usuarioModel) {
        return new AnuncioResponse(anuncioModel.getId(), anuncioModel.getNomeDoTitulo(), anuncioModel.getUrlFoto(),
                anuncioModel.getDescricaoFoto(), anuncioModel.getValor(), anuncioModel.isSeNegociavel(), anuncioModel.getCategoria(),
                anuncioModel.getQuantidade(), anuncioModel.getEstado(), anuncioModel.getCidade(), anuncioModel.getEntrega(),
                anuncioModel.getStatus(), UsuarioResponse.fromUsuarioModel(usuarioModel));

    }

    public static List<AnuncioResponse> fromAnuncioModelList(List<AnuncioModel> anuncios) {
        List<AnuncioResponse> novaLista = new ArrayList<>();
        for (AnuncioModel anuncio : anuncios) {
            AnuncioResponse novoAnuncio = AnuncioResponse.fromAnuncioModel(anuncio, anuncio.getUsuario());
            novaLista.add(novoAnuncio);
        }
        return novaLista;
    }
}
