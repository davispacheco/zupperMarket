package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UsuarioResponseDetails {
    private Long id;

    private String nomeCompleto;

    private String apelido;

    private String celular;

    private String email;

    public static UsuarioResponseDetails fromUsuarioModel(UsuarioModel usuario) {
        return new UsuarioResponseDetails(usuario.getId(), usuario.getNomeCompleto(), usuario.getApelido(), usuario.getCelular(), usuario.getEmail());
    }

    public static List<UsuarioResponseDetails> fromUsuarioModelList(List<UsuarioModel> usuarios) {
        List<UsuarioResponseDetails> novaLista = new ArrayList<>();
        for (UsuarioModel usuario : usuarios) {
            UsuarioResponseDetails novoUsuario = UsuarioResponseDetails.fromUsuarioModel(usuario);
            novaLista.add(novoUsuario);
        }
        return novaLista;
    }
}
