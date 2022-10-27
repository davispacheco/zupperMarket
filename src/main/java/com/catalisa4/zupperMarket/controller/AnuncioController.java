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
import java.util.List;

@RestController
@RequestMapping(path = "/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public ResponseEntity<List<AnuncioResponse>> buscarAnuncios (){
        List<AnuncioModel> listaDeAnunciosModel = anuncioService.buscarTodosAnuncios();
        return ResponseEntity.ok(AnuncioResponse.fromAnuncioModelList(listaDeAnunciosModel));
    }

    @GetMapping(path = "/status")
    public ResponseEntity<List<AnuncioResponse>> buscarAnunciosPorStatus(@RequestParam Status status) {
        List<AnuncioModel> anuncios = anuncioService.buscarPorStatus(status);
        return ResponseEntity.ok(AnuncioResponse.fromAnuncioModelList(anuncios));
    }

    @GetMapping(path = "/categoria")
    public ResponseEntity<List<AnuncioResponse>> buscarAnunciosPorStatusECategorias(@RequestParam Status status, @RequestParam Categoria categoria){
        List<AnuncioModel> anuncios = anuncioService.buscarPorStatusECategoria(status, categoria);
        return ResponseEntity.ok(AnuncioResponse.fromAnuncioModelList(anuncios));
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<AnuncioModel> buscarAnuncioPorId(@PathVariable Long id){
        return ResponseEntity.ok(anuncioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AnuncioResponse> cadastrarAnuncio(@Valid @RequestBody AnuncioRequest anuncioRequest){
        AnuncioModel anuncio = anuncioService.cadastrarNovoAnuncio(anuncioRequest.toAnuncioModel(), anuncioRequest.getUsuarioId());
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncio);
        return new ResponseEntity<>(anuncioResponse, HttpStatus.CREATED);
    }

    @PutMapping (path = "/{id}")
    public ResponseEntity<AnuncioResponse> alterarAnuncio(@RequestBody AnuncioRequest anuncioRequest, @PathVariable Long id) {
        AnuncioModel anuncioAlterado = anuncioService.alterarAnuncio(anuncioRequest.toAnuncioModel());
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncioAlterado);
        return  ResponseEntity.ok(anuncioResponse); //verificar se está correto
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AnuncioResponse> alterarStatusDoAnuncio(@RequestBody AnuncioRequestStatusOnly anuncioRequestStatusOnly, @PathVariable Long id) {
        AnuncioModel anuncioAlterado = anuncioService.alterarStatusAnuncio(anuncioRequestStatusOnly.toAnuncioModel());
        AnuncioResponse anuncioResponse = AnuncioResponse.fromAnuncioModel(anuncioAlterado);
        return  ResponseEntity.ok(anuncioResponse); //verificar se está correto
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarAnuncio(@PathVariable Long id) {
        anuncioService.deletarAnuncio(id);
        return ResponseEntity.noContent().build();
    }
}
