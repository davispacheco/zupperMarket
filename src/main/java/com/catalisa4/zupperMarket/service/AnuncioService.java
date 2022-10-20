package com.catalisa4.zupperMarket.service;


import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.repository.IAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    @Autowired
    private static IAnuncioRepository AnuncioRepository;


    public static List<AnuncioModel> buscarTodosAnuncios() {
        return AnuncioRepository.findAll();
    }

    public Optional<AnuncioModel> buscarPorId(long id) {
        return AnuncioRepository.findById(id);
    }

    public AnuncioModel cadastrarNovoAnuncio(AnuncioModel anuncioModel) {
        return AnuncioRepository.save(anuncioModel);
    }

    public AnuncioModel altereAnuncio(AnuncioModel anuncioModel){
        return AnuncioRepository.save(anuncioModel);
    }

    public void deleteAnuncio(Long id) {
        AnuncioRepository.deleteById(id);
    }

}
