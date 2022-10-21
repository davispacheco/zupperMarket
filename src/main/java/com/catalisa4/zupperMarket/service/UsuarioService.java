package com.catalisa4.zupperMarket.service;

import com.catalisa4.zupperMarket.exception.DataIntegratyViolationException;
import com.catalisa4.zupperMarket.exception.EntityNotFoundException;
import com.catalisa4.zupperMarket.model.UsuarioModel;
import com.catalisa4.zupperMarket.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    public UsuarioModel cadastrar(UsuarioModel usuarioModel) {
        UsuarioModel obj = buscarUsuarioPorEmail(usuarioModel.getEmail());
        validarEmailExistente(obj, usuarioModel.getEmail());
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

    public void validarEmailInexistente(UsuarioModel usuarioModel, String email) {
        if (!email.equalsIgnoreCase(usuarioModel.getEmail())) {
            throw new EntityNotFoundException("E-mail não encontrado: " + email);
        }
    }

    public void validarEmailExistente(UsuarioModel usuarioModel, String email) {
        if (email.equalsIgnoreCase(usuarioModel.getEmail())) {
            throw new DataIntegratyViolationException("E-mail já existe!");
        }
    }

    //Validação(Query method)
    public UsuarioModel buscarUsuarioPorEmail(String email) {
        Optional<UsuarioModel> obj = iUsuarioRepository.findByEmail(email);
        return obj.get();
    }
}
