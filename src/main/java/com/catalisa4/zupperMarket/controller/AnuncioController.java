package com.catalisa4.zupperMarket.controller;


import com.catalisa4.zupperMarket.enums.Categoria;
import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.model.AnuncioModel;

import com.catalisa4.zupperMarket.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping
    public ResponseEntity<List<AnuncioResponse>> buscarAnunciosPorStatusECategorias(@RequestParam(required = false) Status status, @RequestParam(required = false) Categoria categoria) {
        List<AnuncioModel> anuncios;
        if (status != null && categoria == null) {
            anuncios = anuncioService.buscarPorStatus(status);
        } else if (status == null && categoria != null) {
            anuncios = anuncioService.buscarPorCategoria(categoria);
        } else if (status != null && categoria != null) {
            anuncios = anuncioService.buscarPorStatusECategoria(status, categoria);
        } else {
            anuncios = anuncioService.buscarTodos();
        }
        return ResponseEntity.ok(AnuncioResponse.fromAnuncioModelList(anuncios));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/{id}")
    public ResponseEntity<AnuncioResponseDetails> buscarAnuncioPorId(@PathVariable Long id) {
        AnuncioModel anuncio = anuncioService.buscarPorId(id);
        return ResponseEntity.ok(AnuncioResponseDetails.fromAnuncioModel(anuncio, anuncio.getUsuario()));
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping
    public ResponseEntity<AnuncioResponse> cadastrarAnuncio(@Valid @RequestBody AnuncioRequest anuncioRequest) {
        AnuncioModel anuncio = anuncioService.cadastrarNovoAnuncio(anuncioRequest.toAnuncioModel(), anuncioRequest.getUsuarioId());
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncio, anuncio.getUsuario());
        return new ResponseEntity<>(anuncioResponse, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AnuncioResponse> alterarAnuncio(@RequestBody AnuncioRequest anuncioRequest, @PathVariable Long id) {
        AnuncioModel anuncioAlterado = anuncioService.alterarAnuncio(anuncioRequest.toAnuncioModel(), id);
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncioAlterado, anuncioAlterado.getUsuario());
        return ResponseEntity.ok(anuncioResponse); //verificar se está correto
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<AnuncioResponse> alterarStatusDoAnuncio(@RequestBody AnuncioRequestStatusOnly anuncioRequestStatusOnly, @PathVariable Long id) {
        AnuncioModel anuncioAlterado = anuncioService.alterarStatusAnuncio(anuncioRequestStatusOnly.toAnuncioModel(), id);
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncioAlterado, anuncioAlterado.getUsuario());
        return ResponseEntity.ok(anuncioResponse); //verificar se está correto
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarAnuncio(@PathVariable Long id) {
        anuncioService.deletarAnuncio(id);
        return ResponseEntity.noContent().build();
    }
}
