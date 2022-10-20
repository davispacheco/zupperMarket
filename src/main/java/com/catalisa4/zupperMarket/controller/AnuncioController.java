package com.catalisa4.zupperMarket.controller;


import com.catalisa4.zupperMarket.model.AnuncioModel;

import com.catalisa4.zupperMarket.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public ResponseEntity<List<AnuncioResponse>> buscarAnuncios (){
        List<AnuncioModel> listaDeAnunciosModel = anuncioService.buscarTodosAnuncios();
        List<AnuncioResponse>  listaAnunciosResponse = new ArrayList<>();
        for (AnuncioModel anuncioModel: listaDeAnunciosModel) {
            AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncioModel);
            listaAnunciosResponse.add(anuncioResponse);
        }
        return ResponseEntity.ok(listaAnunciosResponse);
    }

    @GetMapping (path = "/{id}")
    public AnuncioModel buscarAnuncioPorId(@PathVariable Long id){
        return anuncioService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<AnuncioResponse> cadastrarAnuncio(@RequestBody AnuncioRequest anuncioRequest){
        AnuncioModel anuncio = anuncioService.cadastrarNovoAnuncio(anuncioRequest.toAnuncioModel());
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncio);
        return new ResponseEntity<>(anuncioResponse, HttpStatus.CREATED);
    }

    @PutMapping (path = "/{id}")
    public ResponseEntity<AnuncioResponse> alterarAnuncio(@RequestBody AnuncioRequest anuncioRequest, @PathVariable Long id) {
        AnuncioModel anuncioAlterado = anuncioService.alterarAnuncio(anuncioRequest.toAnuncioModel(), id);
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncioAlterado);
        return  ResponseEntity.ok(anuncioResponse); //verificar se está correto
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarAnuncio(@PathVariable Long id) {
        anuncioService.deletarAnuncio(id);
        return ResponseEntity.noContent().build();
    }
}
