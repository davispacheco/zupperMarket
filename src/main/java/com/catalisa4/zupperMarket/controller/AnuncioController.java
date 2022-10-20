package com.catalisa4.zupperMarket.controller;


import com.catalisa4.zupperMarket.model.AnuncioModel;

import com.catalisa4.zupperMarket.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/anuncio")
public class AnuncioController {

    @Autowired
    AnuncioService anunciosService;

    @GetMapping (path = "/anuncios")
    public List<AnuncioModel> buscarAnuncios (){
        return anunciosService.buscarTodosAnuncios();
    }

    @GetMapping (path = "/anuncios/{id}")
    public Optional<AnuncioModel> buscarAnuncioPorId(@PathVariable long id){
        return anunciosService.buscarPorId(id);
    }
    @PostMapping(path = "/anuncios")
    public AnuncioModel cadastrarAnuncio(@RequestBody AnuncioModel anunciosModel){
        return  anunciosService.cadastrarNovoAnuncio(anunciosModel);
    }
    @PutMapping (path = "/anuncios/{id}")
    public AnuncioModel alterarAnuncio(@RequestBody AnuncioModel anunciosModel){
        return anunciosService.alterarAnuncio(anunciosModel);
    }

    @DeleteMapping(path = "/anuncios/{id}")
    public void deletarAnuncio(@PathVariable Long id){
        anunciosService.deletarAnuncio(id);
    }
}