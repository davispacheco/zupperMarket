package com.catalisa4.zupperMarket.controller;


import com.catalisa4.zupperMarket.model.AnunciosModel;
import com.catalisa4.zupperMarket.Service.AnunciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/anuncio")
public class AnunciosController {

    @Autowired
    AnunciosService anunciosService;

    //Cadastro de Anuncio//
 //   @PostMapping(path = "/anuncio/{id}")
   // public ResponseEntity <AnunciosModel>cadastrarAnuncio(@RequestBody AnunciosModel cadastreAnuncio, @PathVariable Long id){
     //   AnunciosModel novoAnuncio =
   /// }


    @GetMapping (path = "/anuncios")
    public List<AnunciosModel> buscarAnuncios (){
        return anunciosService.buscarTodosAnuncios();
    }

    @GetMapping (path = "/anuncios/{id}")
    public Optional<AnunciosModel> buscarAnuncioPorId(@PathVariable long id){
        return anunciosService.buscarPorId(id);
    }
    @PostMapping(path = "/anuncios")
    public AnunciosModel cadastrarAnuncio(@RequestBody AnunciosModel anunciosModel){
        return  anunciosService.cadastrarNovoAnuncio(anunciosModel);
    }
    @PutMapping (path = "/anuncios/{id}")
    public AnunciosModel alterarAnuncio(@RequestBody AnunciosModel anunciosModel){
        return anunciosService.altereAnuncio(anunciosModel);
    }

    @DeleteMapping(path = "/anuncios/{id}")
    public void deletarAnuncio(@PathVariable Long id){
        anunciosService.deleteAnuncio(id);
    }
}
