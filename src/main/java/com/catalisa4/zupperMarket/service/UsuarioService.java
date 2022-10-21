package com.catalisa4.zupperMarket.service;

import com.catalisa4.zupperMarket.exception.EntityNotFoundException;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    public UsuarioModel cadastrar(UsuarioModel usuarioModel){
        return iUsuarioRepository.save(usuarioModel);
    }

    public UsuarioModel exibirUsuarioPorId(Long id) {
        Optional<UsuarioModel> obj = iUsuarioRepository.findById(id);
        obj.orElseThrow((() -> new EntityNotFoundException("Erro: id não encontrado. " + id)));
        return obj.get();
    }

    public UsuarioModel atualizarUsuarioCadastrado(UsuarioModel usuarioModel, Long id) {
        UsuarioModel newUsuario = exibirUsuarioPorId(id);
        newUsuario.setNomeCompleto(usuarioModel.getNomeCompleto());
        newUsuario.setApelido(usuarioModel.getApelido());
        newUsuario.setCelular(usuarioModel.getCelular());
        return iUsuarioRepository.save(newUsuario);
    }

    public void deletarUsuario(Long id) {
        iUsuarioRepository.deleteById(id);
    }

    //Validação(Query method)
    public UsuarioModel buscarUsuarioPorEmail(String email) {
        Optional<UsuarioModel> obj = iUsuarioRepository.findByEmail(email);
        obj.orElseThrow((() -> new EntityNotFoundException("Erro: e-mail não encontrado. " + email)));
        return obj.get();
    }
}
