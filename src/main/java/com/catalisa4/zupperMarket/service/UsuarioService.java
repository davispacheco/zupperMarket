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

    public Optional<UsuarioModel> exibirUsuarioPorId(Long id) {
        return Optional.ofNullable(iUsuarioRepository.findById(id).orElseThrow((() -> new EntityNotFoundException("Erro: id não encontrado, impossivel efetuar busca pelo id " + id))));
    }

    public UsuarioModel atualizarUsuarioCadastrado(UsuarioModel usuarioModel, Long id) {
        iUsuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Erro: id não encontrado, não é possvel efetuar alteração" + id));
        return iUsuarioRepository.save(usuarioModel);
    }

    public void deletarUsuario(Long id){
        iUsuarioRepository.deleteById(id);
    }

    //Validação(Query method)
    public boolean existsByEmail(String existsByEmail){
        return iUsuarioRepository.existsBYEmail(existsByEmail);
    }
}
