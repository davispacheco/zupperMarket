package com.catalisa4.zupperMarket.service;


import com.catalisa4.zupperMarket.enums.Status;
import com.catalisa4.zupperMarket.exception.EntityNotFoundException;
import com.catalisa4.zupperMarket.model.AnuncioModel;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IAnuncioRepository;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    @Autowired
    private IAnuncioRepository iAnuncioRepository;


    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    public List<AnuncioModel> buscarTodosAnuncios() {
        return iAnuncioRepository.findAll();
    }

    public AnuncioModel buscarPorId(Long id) {
        Optional<AnuncioModel> obj = iAnuncioRepository.findById(id);
        return obj.get();
    }

    public AnuncioModel cadastrarNovoAnuncio(AnuncioModel anuncioModel, Long id) {
        anuncioModel.setStatus(Status.DISPONIVEL);
        anuncioModel.setDataHoraCriacao(LocalDateTime.now());
        Optional<UsuarioModel> usuario = iUsuarioRepository.findById(id);
        usuario.orElseThrow((() -> new EntityNotFoundException("Usuário com id " + id + " não encontrado.")));
        anuncioModel.setUsuario(usuario.get());
        return iAnuncioRepository.save(anuncioModel);
    }

   /* public AnuncioModel alterarAnuncio(AnuncioModel anuncioModel, Long id) {
        AnuncioModel newAnuncio = buscarPorId(id);
        newAnuncio.setNomeDoTitulo(anuncioModel.getNomeDoTitulo());
        newAnuncio.setDescricao(anuncioModel.getDescricao());
        newAnuncio.setStatus(anuncioModel.getStatus());
        return iAnuncioRepository.save(newAnuncio);
    }*/

    public AnuncioModel alterarAnuncio(AnuncioModel anuncioModel) {

        return iAnuncioRepository.save(anuncioModel);
    }

    public void deletarAnuncio(Long id) {
        iAnuncioRepository.deleteById(id);
    }



}
