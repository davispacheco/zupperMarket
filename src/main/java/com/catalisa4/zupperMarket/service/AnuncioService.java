package com.catalisa4.zupperMarket.service;


import com.catalisa4.zupperMarket.enums.Categoria;
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

    public List<AnuncioModel> buscarTodos() {
        return iAnuncioRepository.findAll();
    }

    public List<AnuncioModel> buscarPorStatus(Status status) {
        return iAnuncioRepository.findByStatus(status);
    }

    public List<AnuncioModel> buscarPorCategoria(Categoria categoria) {
        return iAnuncioRepository.findByCategoria(categoria);
    }

    public List<AnuncioModel> buscarPorStatusECategoria(Status status, Categoria categoria) {
        return iAnuncioRepository.findByStatusAndCategoria(status, categoria);
    }

    public AnuncioModel buscarPorId(Long id) {
        Optional<AnuncioModel> obj = iAnuncioRepository.findById(id);
        obj.orElseThrow((() -> new EntityNotFoundException("Erro: id não encontrado. " + id)));
        return obj.get();
    }

    public AnuncioModel cadastrarNovoAnuncio(AnuncioModel anuncioModel, Long idUsuario) {
        anuncioModel.setStatus(Status.DISPONIVEL);
        anuncioModel.setDataHoraCriacao(LocalDateTime.now());
        Optional<UsuarioModel> usuario = iUsuarioRepository.findById(idUsuario);

        usuario.orElseThrow((() -> new EntityNotFoundException("Usuário com o idUsuario " + idUsuario + " não encontrado.")));


        anuncioModel.setUsuario(usuario.get());
        return iAnuncioRepository.save(anuncioModel);
    }

    public AnuncioModel alterarAnuncio(AnuncioModel anuncioModel, Long id) {
        AnuncioModel newAnuncio = buscarPorId(id);
        anuncioModel.setStatus(newAnuncio.getStatus());
        anuncioModel.setDataHoraCriacao(newAnuncio.getDataHoraCriacao());
        anuncioModel.setUsuario(newAnuncio.getUsuario());
        return iAnuncioRepository.save(anuncioModel);
    }

    public AnuncioModel alterarStatusAnuncio(AnuncioModel anuncioModel, Long id) {
        AnuncioModel newAnuncio = buscarPorId(id);
        newAnuncio.setStatus(anuncioModel.getStatus());
        return iAnuncioRepository.save(newAnuncio);
    }

    public void deletarAnuncio(Long id) {
        iAnuncioRepository.deleteById(id);
    }


}
