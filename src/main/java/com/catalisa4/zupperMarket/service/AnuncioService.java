package com.catalisa4.zupperMarket.Service;

import com.catalisa4.zupperMarket.model.AnunciosModel;
import com.catalisa4.zupperMarket.Repository.AnunciosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    @Autowired
    private static AnunciosRepository anunciosRepository;


    public static List<AnunciosModel> buscarTodosAnuncios() {
        return anunciosRepository.findAll();
    }

    public Optional<AnunciosModel> buscarPorId(long id) {
        return anunciosRepository.findById(id);
    }

    public AnunciosModel cadastrarNovoAnuncio(AnunciosModel anunciosModel) {
        return anunciosRepository.save(anunciosModel);
    }

    public AnunciosModel altereAnuncio(AnunciosModel anunciosModel){
        return anunciosRepository.save(anunciosModel);
    }

    public void deleteAnuncio(Long codigo) {
        anunciosRepository.deleteById(codigo);
    }

}