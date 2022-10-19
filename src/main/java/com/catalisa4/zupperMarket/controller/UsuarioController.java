package com.catalisa4.zupperMarket.controller;

import com.catalisa4.zupperMarket.DTO.UsuarioRequest;
import com.catalisa4.zupperMarket.DTO.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return null;
    }
}
