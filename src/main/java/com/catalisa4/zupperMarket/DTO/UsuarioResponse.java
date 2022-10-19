package com.catalisa4.zupperMarket.DTO;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UsuarioResponse {
    private Long id;

    private String nomeCompleto;

    private String apelido;

    private String email;

    public static UsuarioResponse converterParaResponse(UsuarioModel usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNomeCompleto(), usuario.getApelido(), usuario.getEmail());
    }

    public static List<UsuarioResponse> converterLista(List<UsuarioModel> usuarios) {
        List<UsuarioResponse> novaLista = new ArrayList<>();
        for (UsuarioModel usuario : usuarios) {
            UsuarioResponse novoUsuario = new UsuarioResponse(usuario.getId(), usuario.getNomeCompleto(), usuario.getApelido(), usuario.getEmail());
            novaLista.add(novoUsuario);
        }
        return novaLista;
    }
}
