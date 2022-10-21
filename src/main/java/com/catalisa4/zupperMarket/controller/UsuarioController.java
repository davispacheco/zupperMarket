package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioModel usuario = usuarioService.cadastrar(usuarioRequest.toUsuarioModel());
        return ResponseEntity.ok(UsuarioResponse.converterParaResponse(usuario));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@RequestBody UsuarioModel usuarioModel, @PathVariable Long id) {
        UsuarioModel usuario = usuarioService.atualizarUsuarioCadastrado(usuarioModel, id);
        return ResponseEntity.ok(UsuarioResponse.converterParaResponse(usuario));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
