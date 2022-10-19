package com.catalisa4.zupperMarket.ControlleRAnuncios;


import com.catalisa4.zupperMarket.ModelAnuncios.AnunciosModel;
import com.catalisa4.zupperMarket.ServiceAnuncios.AnunciosService;
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


    @GetMapping (path = "/anuncio")
    public List<AnunciosModel> buscarAnuncios (){
        return anunciosService.buscarTodosAnuncios();
    }

    @GetMapping (path = "/anuncio/{id}")
    public Optional<AnunciosModel> burscarAnuncioPorId (@PathVariable long id){
        return anunciosService.buscarPorId(id);
    }
    @PostMapping(path = "/anuncio")
    public AnunciosModel cadastrarAnuncio(@RequestBody AnunciosModel anunciosModel){
        return  anunciosService.cadastrarNovoAnuncio(anunciosModel);
    }

    @DeleteMapping(path = "/anuncio/{id}")
    public void deletarAnuncio(@PathVariable Long id){
        anunciosService.deleteAnuncio(id);
    }
}
