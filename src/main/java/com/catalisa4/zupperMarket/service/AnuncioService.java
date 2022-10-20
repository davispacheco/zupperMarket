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
    private static IAnuncioRepository iAnuncioRepository;


    public List<AnuncioModel> buscarTodosAnuncios() {
        return iAnuncioRepository.findAll();
    }

    public AnuncioModel buscarPorId(Long id) {
        Optional<AnuncioModel> obj = iAnuncioRepository.findById(id);
        return obj.get();
    }

    public AnuncioModel cadastrarNovoAnuncio(AnuncioModel anuncioModel) {
        return iAnuncioRepository.save(anuncioModel);
    }

    public AnuncioModel alterarAnuncio(AnuncioModel anuncioModel, Long id) {
        AnuncioModel newAnuncio = buscarPorId(id);
        newAnuncio.setNomeDoTitulo(anuncioModel.getNomeDoTitulo());
        newAnuncio.setDescricao(anuncioModel.getDescricao());
        newAnuncio.setStatus(anuncioModel.getStatus());
        return iAnuncioRepository.save(newAnuncio);
    }

    public void deletarAnuncio(Long id) {
        iAnuncioRepository.deleteById(id);
    }



}
